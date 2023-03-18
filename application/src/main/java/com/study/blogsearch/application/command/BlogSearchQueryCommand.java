package com.study.blogsearch.application.command;

import com.study.blogsearch.domain.entity.vo.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class BlogSearchQueryCommand {

    private String query;
    private SortOrder sort;
    private int start;
    public static BlogSearchQueryCommand of(String query, String sort, int start) {
        return BlogSearchQueryCommand.builder()
                .query(query)
                .sort(SortOrder.fromString(sort))
                .start(start)
                .build();
    }
}
