package com.study.blogsearch.infrastructure.serverapi.dto;

import com.study.blogsearch.domain.entity.vo.SortOrder;
import com.study.blogsearch.domain.extapi.command.BlogSearchQuery;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoBlogSearchRequest {

    private String query;

    @Builder.Default
    private String sort = SortOrder.ACCURACY.name();

    public static KakaoBlogSearchRequest from(BlogSearchQuery query) {
        return KakaoBlogSearchRequest.builder()
                .query(query.getQuery())
                .sort(query.getSortOrder().name())
                .build();
    }
}
