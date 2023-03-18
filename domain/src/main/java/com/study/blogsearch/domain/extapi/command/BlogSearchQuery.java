package com.study.blogsearch.domain.extapi.command;

import com.study.blogsearch.domain.entity.vo.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class BlogSearchQuery {
    private String query; // 검색어
    private SortOrder sortOrder; // 조회 순서 (정확도순, 최신순)
}
