package com.study.blogsearch.api.controller;

import com.study.blogsearch.api.dto.SearchHistoryResponse;
import com.study.blogsearch.application.usecase.SearchHistoryUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class SearchHistoryController {

    private final SearchHistoryUsecase searchHistoryUsecase;

    @GetMapping("/popular")
    public Flux<SearchHistoryResponse> popularHistory(@RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate date) {
        return searchHistoryUsecase.findPopularSearch(date)
                .index()
                .map(tuple -> SearchHistoryResponse.builder()
                        .rank((int) (tuple.getT1() + 1))
                        .keyword(tuple.getT2().getKeyword())
                        .count(tuple.getT2().getCount())
                        .build());
    }
}

