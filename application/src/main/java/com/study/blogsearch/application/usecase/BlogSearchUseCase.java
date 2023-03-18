package com.study.blogsearch.application.usecase;

import com.study.blogsearch.application.command.BlogSearchQueryCommand;
import com.study.blogsearch.domain.entity.BlogSearchResult;
import reactor.core.publisher.Mono;

public interface BlogSearchUseCase {

    Mono<BlogSearchResult> searchBlog(BlogSearchQueryCommand command);
}
