package com.openai.api.service;

import com.alibaba.fastjson.JSONObject;
import com.core.bean.ResultFactory;
import com.core.bean.chatgpt.GPTResp;
import com.core.bean.chatgpt.PromptData;
import com.openai.api.OpenAIAPIEnum;
import com.openai.api.component.ChatSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Configuration
public class BaseService {
    @Resource
    RestTemplate restTemplate;

    private SseEmitter emitter = new SseEmitter();
    private String apiKey = "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";

    private static final String LOCAL_PROXY_HOSTNAME = "127.0.0.1"; // 本地Clash代理主机名
    private static final int LOCAL_PROXY_PORT = 7890; // 本地Clash代理端口
    public ConcurrentHashMap<String, ChatSession> sessionMap = new ConcurrentHashMap<>(16);

    public String completions(PromptData promptData, String apiKey) {
        log.info("进入会话.." + promptData.getMessages()[0]);
        if (apiKey != null) {
            this.apiKey = apiKey;
        }
        ChatSession chatSession;
        //首先获取上下文
        if (sessionMap.contains(apiKey)) {
            chatSession = sessionMap.get(apiKey);
            PromptData.Content message = chatSession.getPromptData().getMessages()[0];
            StringBuilder prompts = new StringBuilder(message.getContent());
            String inputPromt = promptData.getMessages()[0].getContent();
            prompts.append(inputPromt);
            promptData.setMessages(promptData.getMessages());
        } else {
            chatSession = new ChatSession();
            chatSession.setPromptData(promptData);
            chatSession.setApiKey(apiKey);
            sessionMap.put(chatSession.getApiKey(), chatSession);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        headers.add("Content-Type", "application/json");
        HttpEntity<PromptData> httpEntity = new HttpEntity<>(promptData, headers);
        log.info(JSONObject.toJSONString(promptData));
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(OpenAIAPIEnum.COMPLETIONS.getUrl(), httpEntity, JSONObject.class);
        log.info(JSONObject.toJSONString(jsonObjectResponseEntity));
        StringBuilder result = new StringBuilder();
        if (jsonObjectResponseEntity.getBody() != null) {
            GPTResp resp = JSONObject.parseObject(jsonObjectResponseEntity.getBody().toString(), GPTResp.class);
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

    public SseEmitter getEvents() {
        log.info("emitter in..");
        return emitter;
    }

    public void completions(PromptData promptData) {
        log.info("进入请求:" + promptData.toString());
        promptData.setStream(true);
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient
                        .proxy(proxy -> proxy
                                .type(ProxyProvider.Proxy.HTTP)
                                .host(LOCAL_PROXY_HOSTNAME)
                                .port(LOCAL_PROXY_PORT)));
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        emitter.onCompletion(() -> {
            log.info("sse 已关闭");
            emitter = new SseEmitter();
        });
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
                                emitter.send(data.getBytes(StandardCharsets.UTF_8));
                            } catch (IOException e) {
                                e.printStackTrace();
                                log.error(e.getMessage(), e);
                            }
                        }
                );
    }
}
