package com.study.blogsearch.infrastructure.serverapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.infrastructure.serverapi.dto.KakaoBlogSearchRequest;
import com.study.blogsearch.infrastructure.serverapi.dto.NaverBlogSearchRequest;
import com.study.blogsearch.infrastructure.serverapi.dto.NaverBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class BlogSearchApi implements BlogSearch {

    private final KakaoBlogSearch kakaoBlogSearch;
    private final NaverBlogSearch naverBlogSearch;

    @Override
    public BlogSearchResult searchBlog(BlogSearchQuery query) {
        return kakaoBlogSearch.callKakaoAPI(KakaoBlogSearchRequest.from(query))
                .map(kakaoBlogSearchResponse -> kakaoBlogSearchResponse.toDomainEntity(query.getStart()))
                .onErrorResume(e -> naverBlogSearch.callNaverAPI(NaverBlogSearchRequest.from(query)).map(NaverBlogSearchResponse::toDomainEntity))
                .onErrorResume(e2 -> Mono.error(new BlogSearchException(BlogSearchErrorCode.BLOG_SERVER_ERROR)))
                .block();
    }
}
