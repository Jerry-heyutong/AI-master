package com;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetSocketAddress;
import java.net.Proxy;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.*")
@EnableConfigurationProperties
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		RestTemplate template = builder.build();

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setProxy(
				new Proxy(
						Proxy.Type.HTTP,
						new InetSocketAddress("127.0.0.1", 7890)  //设置代理服务
						//new InetSocketAddress("127.0.0.1", 9090)  //设置代理服务
				)
		);
		requestFactory.setConnectTimeout(10*1000);
		requestFactory.setReadTimeout(10*1000);
		template.setRequestFactory(requestFactory);
		template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return template;
	}
}
