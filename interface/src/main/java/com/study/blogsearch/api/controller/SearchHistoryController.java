package com.study.blogsearch.api.controller;

import com.study.blogsearch.api.dto.SearchHistoryResponse;
import com.study.blogsearch.application.usecase.SearchHistoryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchHistoryController {

    private final SearchHistoryUsecase searchHistoryUsecase;

    @GetMapping("/popular-keywords")
    public List<SearchHistoryResponse> popularHistory() {
        final var popularSearchList = searchHistoryUsecase.findPopularSearch();
        return IntStream.range(0, popularSearchList.size())
                .mapToObj(i -> SearchHistoryResponse.builder()
                        .rank(i + 1)
                        .keyword(popularSearchList.get(i).getKeyword())
                        .count(popularSearchList.get(i).getCount())
                        .build())
                .collect(Collectors.toList());
    }
}

