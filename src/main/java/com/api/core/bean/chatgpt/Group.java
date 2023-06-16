package com.api.core.bean.chatgpt;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class Group {
    String groupId;
    PromptData promptData;
    ConcurrentLinkedQueue<SseEmitter> clientQueue;
}
