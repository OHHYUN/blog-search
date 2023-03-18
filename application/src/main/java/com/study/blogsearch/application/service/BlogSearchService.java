package com.study.blogsearch.application.service;

import com.study.blogsearch.application.command.BlogSearchQueryCommand;


import com.study.blogsearch.application.usecase.BlogSearchUseCase;
import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private final BlogSearch blogSearch;

    @Override
    public Mono<BlogSearchResult> searchBlog(BlogSearchQueryCommand command) {
        // 검색 했다는 Event를 어떻게 태울까?
        //이곳에서 에러가 난다면 naverBlogSearch로 가게 만들어야한다!!
        return blogSearch.searchBlog(BlogSearchQuery.builder()
                        .query(command.getQuery())
                        .sortOrder(command.getSort())
                        .start(command.getStart())
                        .build());
    }
}
