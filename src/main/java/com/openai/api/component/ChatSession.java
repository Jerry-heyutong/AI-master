package com.openai.api.component;

import com.core.bean.chatgpt.PromptData;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
public class ChatSession {
    PromptData promptData;
    String apiKey;
}
