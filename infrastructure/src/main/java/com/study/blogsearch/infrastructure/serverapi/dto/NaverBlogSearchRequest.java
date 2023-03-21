package com.study.blogsearch.infrastructure.serverapi.dto;

import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NaverBlogSearchRequest {

    private String query;
    @Builder.Default
    private Integer display = 10;
    private Integer start;
    private String sort;

    public static NaverBlogSearchRequest from(BlogSearchQuery query) {
        return NaverBlogSearchRequest.builder()
                .query(query.getQuery())
                .start(query.getStart())
                .sort(query.getSortOrder().getSortNameForNaver())
                .build();
    }

}
