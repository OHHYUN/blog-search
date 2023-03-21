package com.study.blogsearch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchHistory {
    private String keyword;

    private LocalDate date;

    @Builder.Default
    private Long count = 0L;

}
