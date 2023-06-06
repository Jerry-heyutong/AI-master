package com.core.bean.chatgpt;

import lombok.Data;

import java.io.Serializable;

@Data
public class GPTResp implements Serializable {
    private static final long serialVersionUID = 1L;

    private long created;
    private Usage usage;
    private String model;
    private String id;
    private Choice[] choices;
    private String object;

    // getters and setters
    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        //getters and setters
    }

    @Data
    public static class Choice {
        private Message message;
        private String finishReson;
        private Integer index;
    }

    private int index;
    private String finish_reason;
    @Data
    public static class Message {
        private String role;
        private String content;
    }
    //getters and setters
}

