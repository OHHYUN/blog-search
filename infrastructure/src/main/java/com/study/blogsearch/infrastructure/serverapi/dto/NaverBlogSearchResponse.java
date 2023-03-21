package com.study.blogsearch.infrastructure.serverapi.dto;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.vo.Meta;
import com.study.blogsearch.domain.entity.vo.SearchSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NaverBlogSearchResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;

    private List<NaverBlogSearchItem> items;

    public BlogSearchResult toDomainEntity() {
        return BlogSearchResult.builder()
                .meta(Meta.builder()
                        .searchSource(SearchSource.NAVER)
                        .totalItems(this.total)
                        .currentPage(this.start)
                        .build())
                .blogPosts(this.items.stream().map(NaverBlogSearchItem::toDomainEntity).collect(Collectors.toList()))
                .build();
    }
}
