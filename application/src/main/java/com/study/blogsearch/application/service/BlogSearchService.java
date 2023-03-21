package com.study.blogsearch.application.service;

import com.study.blogsearch.application.command.BlogSearchQueryCommand;
import com.study.blogsearch.application.usecase.BlogSearchUseCase;
import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    @Qualifier("kakaoBlogSearch")
    private final BlogSearch kakaoBlogSearch;

    @Qualifier("naverBlogSearch")
    private final BlogSearch naverBlogSearch;

    private final SearchHistoryRepository repository;

    @Override
    public Mono<BlogSearchResult> searchBlog(BlogSearchQueryCommand command) {
        Mono<Void> saveHistoryMono = saveHistory(command).subscribeOn(Schedulers.boundedElastic());
        // 검색 했다는 Event를 어떻게 태울까?
        //이곳에서 에러가 난다면 naverBlogSearch로 가게 만들어야한다!!
        BlogSearchQuery blogSearchQuery = BlogSearchQuery.builder()
                .query(command.getQuery())
                .sortOrder(command.getSort())
                .start(command.getStart())
                .build();

        Mono<BlogSearchResult> blogSearchResultMono = kakaoBlogSearch.searchBlog(blogSearchQuery)
                .onErrorResume(e -> naverBlogSearch.searchBlog(blogSearchQuery))
                .onErrorResume(e2 -> Mono.error(new BlogSearchException(BlogSearchErrorCode.BLOG_SERVER_ERROR)));
        return Mono.when(saveHistoryMono, blogSearchResultMono)
                .then(blogSearchResultMono);
    }

    public Mono<Void> saveHistory(BlogSearchQueryCommand command) {
        return Mono.fromCallable(() -> {
            String keyword = command.getQuery();
            SearchHistory searchHistory = SearchHistory.builder()
                    .keyword(keyword)
                    .date(LocalDate.now())
                    .build();
            repository.findAndUpdate(searchHistory);
            return null;
        });
    }

}
