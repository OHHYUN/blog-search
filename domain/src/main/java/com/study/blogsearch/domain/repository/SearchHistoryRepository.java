package com.study.blogsearch.domain.repository;

import com.study.blogsearch.domain.entity.SearchHistory;

import java.util.List;

public interface SearchHistoryRepository {
    SearchHistory findAndUpdate(SearchHistory searchHistory);
    List<SearchHistory> findTop10Keyword();

}
