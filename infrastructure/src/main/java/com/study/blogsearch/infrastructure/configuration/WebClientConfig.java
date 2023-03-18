package com.study.blogsearch.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean(name = "kakaoWebClient")
    public WebClient kakaoWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("https://dapi.kakao.com/v2/search/blog")
                .build();
    }

    @Bean(name = "naverWebClient")
    public WebClient naverWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("https://openapi.naver.com/v1/search/blog.json")
                .build();
    }
}
