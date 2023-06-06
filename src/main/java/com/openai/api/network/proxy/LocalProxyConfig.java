package com.openai.api.network.proxy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "network.proxy")
@Data
public class LocalProxyConfig {
    private Clash clash;

    @Data
    @Configuration
    public static class Clash{
        private String hostname = "127.0.0.1"; // 本地Clash代理主机名
        private Integer port = 7890; // 本地Clash代理端口
    }
}
