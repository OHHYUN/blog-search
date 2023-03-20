package com.study.blogsearch.application.service;

import com.study.blogsearch.application.usecase.SearchHistoryUsecase;
import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SearchHistoryService implements SearchHistoryUsecase {

    private final SearchHistoryRepository repository;

    @Override
    public Flux<SearchHistory> findPopularSearch(LocalDate date) {
        return Flux.defer(()-> Flux.fromIterable(repository.findTop10Keyword(date)));
    }
}
