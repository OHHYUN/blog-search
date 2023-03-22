package com.study.blogsearch.application.service;

import com.study.blogsearch.application.command.BlogSearchQueryCommand;
import com.study.blogsearch.application.usecase.BlogSearchUseCase;
import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.extapi.BlogSearch;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements BlogSearchUseCase {

    private final BlogSearch blogSearch;
    private final SearchHistoryRepository repository;

    @Override
    public BlogSearchResult searchBlog(BlogSearchQueryCommand command) {
        saveHistory(command);
        // 검색 했다는 Event를 어떻게 태울까?
        //이곳에서 에러가 난다면 naverBlogSearch로 가게 만들어야한다!!
        final var blogSearchQuery = BlogSearchQuery.builder()
                .query(command.getQuery())
                .sortOrder(command.getSort())
                .start(command.getStart())
                .build();

        return blogSearch.searchBlog(blogSearchQuery);
    }

    public void saveHistory(BlogSearchQueryCommand command) {
            String keyword = command.getQuery();
            SearchHistory searchHistory = SearchHistory.builder()
                    .keyword(keyword)
                    .date(LocalDate.now())
                    .build();
            repository.findAndUpdate(searchHistory);
    }

}
