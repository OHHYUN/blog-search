package com.study.blogsearch.application.service;

import com.study.blogsearch.application.usecase.SearchHistoryUsecase;
import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchHistoryService implements SearchHistoryUsecase {

    private final SearchHistoryRepository repository;

    @Override
    public List<SearchHistory> findPopularSearch(LocalDate date) {
        return repository.findTop10Keyword(date);
    }
}
