package com.study.blogsearch.domain.extapi;

import com.study.blogsearch.domain.entity.BlogSearchResult;

import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import reactor.core.publisher.Mono;

public interface BlogSearch {
    Mono<BlogSearchResult> searchBlog(BlogSearchQuery query);

}
