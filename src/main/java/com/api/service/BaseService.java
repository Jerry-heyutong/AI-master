package com.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.api.core.bean.chatgpt.Content;
import com.api.core.bean.chatgpt.GPTResp;
import com.api.core.bean.chatgpt.Group;
import com.api.core.bean.chatgpt.PromptData;
import com.api.enums.OpenAIAPIEnum;
import com.api.proxy.LocalProxyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
@Configuration
public class BaseService {
    @Resource
    RestTemplate restTemplate;
    @Resource
    LocalProxyConfig.Clash proxyConfig;

    /**
     * 本地会话缓存
     * apiKey
     * -- group1
     * [sse1,sse2,sse3]
     * -- group2
     * [sse1,sse2,sse3]
     */
    public final Map<String, ConcurrentHashMap<String, Group>> sessionMap = new ConcurrentHashMap<>(16);

    public SseEmitter createEmitter(String apiKey, String groupId) throws IOException {
        log.info("emitter in..");
        ConcurrentHashMap<String, Group> groupMap = sessionMap.get(apiKey);
        if (groupMap == null) {
            //初始化会话组
            groupMap = new ConcurrentHashMap<>();
            sessionMap.put(apiKey, groupMap);
        }
        Group group = sessionMap.get(apiKey).get(groupId);
        if (group == null) {
            //首次发送并接收到了服务器的请求,为初始化会话组添加SSE客户端和会话上下文
            group = new Group();
            group.setGroupId(groupId);
            ConcurrentLinkedQueue<SseEmitter> queue = new ConcurrentLinkedQueue<>();
            SseEmitter emitter = new SseEmitter();
            emitter.onCompletion(() -> queue.remove(emitter));
            emitter.onTimeout(() -> queue.remove(emitter));
            queue.add(emitter);
            group.setClientQueue(queue);
            PromptData promptData = new PromptData();
            promptData.setStream(true);
            promptData.setMessages(new ArrayList<>());
            promptData.setModel("gpt-3.5-turbo");
            group.setPromptData(promptData);
            groupMap.put(groupId, group);
            sessionMap.put(apiKey, groupMap);
            return emitter;
        } else {
            ConcurrentLinkedQueue<SseEmitter> queue = group.getClientQueue();
            if (queue == null) {
                queue = new ConcurrentLinkedQueue<>();
            }
            final ConcurrentLinkedQueue<SseEmitter> finalQueue = queue;
            SseEmitter emitter = new SseEmitter();
            emitter.onCompletion(() -> finalQueue.remove(emitter));
            emitter.onTimeout(() -> finalQueue.remove(emitter));
            queue.add(emitter);
            return emitter;
        }
    }

    public String completions(@NotNull PromptData promptData, @NotNull String apiKey) {
        log.info("进入会话..");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<PromptData> httpEntity = new HttpEntity<>(promptData, headers);
        log.info("会话信息:" + JSON.toJSONString(promptData));
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(OpenAIAPIEnum.COMPLETIONS.getUrl(), httpEntity, JSONObject.class);
        log.info("返回:" + JSON.toJSONString(jsonObjectResponseEntity));
        StringBuffer result = new StringBuffer();
        JSONObject body = jsonObjectResponseEntity.getBody();
        if (body != null) {
            GPTResp resp = JSON.parseObject(body.toString(), GPTResp.class);
            GPTResp.Choice[] choices = resp.getChoices();
            Content content = new Content();
            for (GPTResp.Choice choice : choices) {
                String text = choice.getMessage().getContent();
                log.info(text);
                result.append(text);
            }
            content.setContent(result.toString());
        } else {
            log.warn(jsonObjectResponseEntity.toString());
        }
        return result.toString();
    }

    public void completions(@NotNull PromptData promptData, @NotNull String groupId, @NotNull String apiKey) {
        log.info("apiKey=" + apiKey + ",groupId=" + groupId);
        log.info("请求:promptData=" + promptData);
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.HTTP)
                                .host(proxyConfig.getHostname())
                                .port(proxyConfig.getPort())));
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        BodyInserter<PromptData, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromObject(promptData);
        webClient.post()
                .uri(OpenAIAPIEnum.COMPLETIONS.getUrl())
                .contentType(MediaType.APPLICATION_JSON).body(bodyInserter)
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(data ->
                        {
                            try {
                                log.info(data);
                                Group group = sessionMap.get(apiKey).get(groupId);
                                if (group != null) {
                                    if("[DONE]".equals(data)){
                                        ConcurrentLinkedQueue<SseEmitter> sseEmitters = group.getClientQueue();
                                        if (sseEmitters == null) {
                                            createEmitter(apiKey, groupId);
                                        }else {
                                            for (SseEmitter emitter : sseEmitters) {
                                                emitter.send(data);
                                            }
                                        }
                                        return;
                                    }
                                    JSONObject dataJson = JSON.parseObject(data);
                                    JSONArray choices = dataJson.getJSONArray("choices");
                                    JSONObject promptJson = choices.getJSONObject(0);
                                    JSONObject delta = promptJson.getJSONObject("delta");
                                    String content = delta.getString("content");
                                    String assistant = delta.getString("role");
                                    ConcurrentLinkedQueue<SseEmitter> sseEmitters = group.getClientQueue();
                                    if (sseEmitters == null) {
                                        createEmitter(apiKey, groupId);
                                    }
                                    List<Content> messages = group.getPromptData().getMessages();
                                    if ("assistant".equals(assistant)) {
                                        //gpt返回的流开头
                                        Content message = new Content();
                                        message.setRole(assistant);
                                        message.setContent("");
                                        messages.add(message);
                                    }else {
                                        Content message = messages.get(messages.size() - 1);
                                        String contentOld = message.getContent();
                                        if (!StringUtils.isEmpty(content)) {
                                            for (SseEmitter emitter : sseEmitters) {
                                                message.setContent(contentOld + content);
                                                emitter.send(content);
                                            }
                                        }
                                    }
                                } else {
                                    log.error("未找到会话信息!apikey=" + apiKey);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                log.error(e.getMessage(), e);
                            }
                        }
                );
    }
}
