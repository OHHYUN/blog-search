package com.study.blogsearch.api.controller;

import com.study.blogsearch.api.dto.SearchHistoryResponse;
import com.study.blogsearch.application.usecase.SearchHistoryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class SearchHistoryController {

    private final SearchHistoryUsecase searchHistoryUsecase;

    @GetMapping("/popular")
    public List<SearchHistoryResponse> popularHistory(@RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate date) {
        final var popularSearchList = searchHistoryUsecase.findPopularSearch(date);
        return IntStream.range(0, popularSearchList.size())
                .mapToObj(i -> SearchHistoryResponse.builder()
                        .rank(i + 1)
                        .keyword(popularSearchList.get(i).getKeyword())
                        .count(popularSearchList.get(i).getCount())
                        .build())
                .collect(Collectors.toList());
    }
}

