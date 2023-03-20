package com.study.blogsearch.domain.repository;

import com.study.blogsearch.domain.entity.SearchHistory;

import java.time.LocalDate;
import java.util.List;

public interface SearchHistoryRepository {

    SearchHistory saveSearchHistory(SearchHistory searchHistory);

    SearchHistory findSearchHistory(SearchHistory searchHistory);
    List<SearchHistory> findTop10Keyword(LocalDate date);

}
