package com.study.blogsearch.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Value("${app.naver.client-id}")
    private String naverClientId;

    @Value("${app.naver.client-secret}")
    private String naverClientSecret;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean(name = "kakaoWebClient")
    public WebClient kakaoWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey)
                .baseUrl("https://dapi.kakao.com/")
                .build();
    }

    @Bean(name = "naverWebClient")
    public WebClient naverWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("https://openapi.naver.com/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Naver-Client-Id", naverClientId)
                .defaultHeader("X-Naver-Client-Secret", naverClientSecret)
                .build();
    }
}
