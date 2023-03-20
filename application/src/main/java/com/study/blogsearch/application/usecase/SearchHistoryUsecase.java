package com.study.blogsearch.application.usecase;

import com.study.blogsearch.domain.entity.SearchHistory;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface SearchHistoryUsecase {

    Flux<SearchHistory> findPopularSearch(LocalDate date);
}
