package com.study.blogsearch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchHistoryResponse {
    private int rank;
    private String keyword;
    private long count;
}
