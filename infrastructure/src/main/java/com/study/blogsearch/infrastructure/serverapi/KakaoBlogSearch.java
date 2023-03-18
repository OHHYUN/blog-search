package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.infrastructure.dto.KakaoBlogSearchRequest;
import com.study.blogsearch.infrastructure.dto.KakaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class KakaoBlogSearch implements BlogSearch {

    private final WebClient kakaoWebClient;

    @Value("${app.kakao.rest-api-key}")
    private String kakaoRestApiKey;

    @Override
    public Mono<BlogSearchResult> searchBlog(BlogSearchQuery query) {
        return callKakaoAPI(KakaoBlogSearchRequest.from(query)).map(kakaoBlogSearchResponse -> kakaoBlogSearchResponse.toDomainEntity());
    }
    public Mono<KakaoBlogSearchResponse> callKakaoAPI(KakaoBlogSearchRequest request) {
        return kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", request.getQuery())
                        .queryParam("sort", request.getSort())
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey)
                .retrieve()
                .bodyToMono(KakaoBlogSearchResponse.class);
    }


}
