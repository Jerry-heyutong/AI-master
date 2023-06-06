package com.openai.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.core.bean.chatgpt.GPTResp;
import com.core.bean.chatgpt.PromptData;
import com.openai.api.enums.OpenAIAPIEnum;
import com.openai.api.network.proxy.LocalProxyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
     * apiKey
     * -- group1
     * [sse1,sse2,sse3]
     * -- group1
     * [sse1,sse2,sse3]
     */
    public final Map<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<SseEmitter>>> sessionMap = new ConcurrentHashMap<>(16);

    public SseEmitter subscribe(String apiKey, String groupId) throws IOException {
        log.info("emitter in..");
        if (!sessionMap.containsKey(apiKey)) {
            ConcurrentLinkedQueue<SseEmitter> queue = new ConcurrentLinkedQueue<>();
            SseEmitter emitter = new SseEmitter();
            emitter.onCompletion(() -> queue.remove(emitter));
            emitter.onTimeout(() -> queue.remove(emitter));
            queue.add(emitter);
            ConcurrentHashMap<String, ConcurrentLinkedQueue<SseEmitter>> groupMap = new ConcurrentHashMap<>();
            groupMap.put(groupId, queue);
            sessionMap.put(apiKey, groupMap);
            return emitter;
        } else {
            ConcurrentHashMap<String, ConcurrentLinkedQueue<SseEmitter>> stringConcurrentLinkedQueueConcurrentHashMap = sessionMap.get(apiKey);
            ConcurrentLinkedQueue<SseEmitter> queue = stringConcurrentLinkedQueueConcurrentHashMap.get(groupId);
            if (queue == null) {
                queue = new ConcurrentLinkedQueue<>();
            }
            final ConcurrentLinkedQueue<SseEmitter> finalQueue = queue;
            SseEmitter emitter = new SseEmitter();
            emitter.onCompletion(() -> finalQueue.remove(emitter));
            emitter.onTimeout(() -> finalQueue.remove(emitter));
            queue.add(emitter);
            stringConcurrentLinkedQueueConcurrentHashMap.put(groupId, queue);
            return emitter;
        }
    }

    public String completions(@NotNull PromptData promptData, @NotNull String apiKey) {
        log.info("进入会话.." + promptData.getMessages()[0]);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<PromptData> httpEntity = new HttpEntity<>(promptData, headers);
        log.info(JSON.toJSONString(promptData));
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(OpenAIAPIEnum.COMPLETIONS.getUrl(), httpEntity, JSONObject.class);
        log.info(JSON.toJSONString(jsonObjectResponseEntity));
        StringBuilder result = new StringBuilder();
        JSONObject body = jsonObjectResponseEntity.getBody();
        if (body != null) {
            GPTResp resp = JSON.parseObject(body.toString(), GPTResp.class);
            GPTResp.Choice[] choices = resp.getChoices();
            for (GPTResp.Choice choice : choices) {
                String text = choice.getMessage().getContent();
                log.info(text);
                result.append(text);
                PromptData.Content[] contents = promptData.getMessages();
                contents[0].setContent(promptData.getMessages()[0].getContent() + text);
            }
        } else {
            log.warn(jsonObjectResponseEntity.toString());
        }
        return result.toString();
    }

    public void completions(@NotNull PromptData promptData, @NotNull String uuid, @NotNull String apiKey) {
        log.info("进入请求:" + promptData.toString() + ";uuid=" + uuid + ";apiKey=" + apiKey);
        promptData.setStream(true);
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.HTTP)
                                .host(proxyConfig.getHostname())
                                .port(proxyConfig.getPort())));
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        webClient.post()
                .uri(OpenAIAPIEnum.COMPLETIONS.getUrl())
                .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(promptData))
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .bodyToFlux(String.class)
                .subscribe(data ->
                        {
                            try {
                                log.info(data);
                                ConcurrentHashMap<String, ConcurrentLinkedQueue<SseEmitter>> stringConcurrentLinkedQueueConcurrentHashMap = sessionMap.get(apiKey);
                                if (stringConcurrentLinkedQueueConcurrentHashMap != null) {
                                    ConcurrentLinkedQueue<SseEmitter> sseEmitters = stringConcurrentLinkedQueueConcurrentHashMap.get(uuid);
                                    if (sseEmitters == null) {
                                        subscribe(apiKey, uuid);
                                    }
                                    for (SseEmitter emitter : sseEmitters) {
                                        emitter.send(data.getBytes(StandardCharsets.UTF_8));
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
