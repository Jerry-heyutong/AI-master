package com.openai.api.component;

import lombok.Data;

@Data
public class ServerEvent {
    private String data;

    public ServerEvent(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}