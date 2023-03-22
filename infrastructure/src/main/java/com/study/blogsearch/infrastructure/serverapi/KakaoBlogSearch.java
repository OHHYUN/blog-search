package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import com.study.blogsearch.infrastructure.serverapi.dto.KakaoBlogSearchRequest;
import com.study.blogsearch.infrastructure.serverapi.dto.KakaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KakaoBlogSearch {

    private final WebClient kakaoWebClient;

    public Mono<KakaoBlogSearchResponse> callKakaoAPI(KakaoBlogSearchRequest request) {
        return kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("v2/search/blog")
                        .queryParam("query", request.getQuery())
                        .queryParam("sort", request.getSort())
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new BlogSearchException(BlogSearchErrorCode.KAKAO_BLOG_SERVER_ERROR)))
                .bodyToMono(KakaoBlogSearchResponse.class)
                .onErrorMap(WebClientResponseException.class, e -> new BlogSearchException(BlogSearchErrorCode.JSON_CONVERT_ERROR))
                .retry(1);
    }


}
