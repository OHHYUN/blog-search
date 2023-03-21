package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.infrastructure.serverapi.dto.NaverBlogSearchRequest;
import com.study.blogsearch.infrastructure.serverapi.dto.NaverBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class NaverBlogSearch implements BlogSearch {

    private final WebClient naverWebClient;
    @Override
    public Mono<BlogSearchResult> searchBlog(BlogSearchQuery query) {
        NaverBlogSearchRequest naverBlogSearchRequest = NaverBlogSearchRequest.from(query);
        return callNaverAPI(naverBlogSearchRequest).map(NaverBlogSearchResponse::toDomainEntity);
    }

    public Mono<NaverBlogSearchResponse> callNaverAPI(NaverBlogSearchRequest request) {
        return naverWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/blog.json")
                        .queryParam("query", request.getQuery())
                        .queryParam("display", request.getDisplay())
                        .queryParam("start", request.getStart())
                        .queryParam("sort", request.getSort())
                        .build())
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new BlogSearchException(BlogSearchErrorCode.NAVER_BLOG_SERVER_ERROR)))
                .bodyToMono(NaverBlogSearchResponse.class)
                .onErrorMap(WebClientResponseException.class, e -> new BlogSearchException(BlogSearchErrorCode.JSON_CONVERT_ERROR))
                .retry(1);
    }


}
