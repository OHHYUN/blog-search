package com.study.blogsearch.application.usecase;

import com.study.blogsearch.domain.entity.SearchHistory;

import java.util.List;

public interface SearchHistoryUsecase {

    List<SearchHistory> findPopularSearch();
}
