package com.api.enums;

public enum OpenAIAPIEnum {
    COMPLETIONS("https://api.openai.com/v1/chat/completions");

    private String url;

    OpenAIAPIEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
