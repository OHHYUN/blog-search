package com.study.blogsearch.infrastructure.dto;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.Meta;
import com.study.blogsearch.domain.entity.vo.SearchSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class KakaoBlogSearchResponse {
    private KakaoBlogSearchMeta meta;
    private List<KakaoBlogSearchDocument> documents;

    public BlogSearchResult toDomainEntity(int start) {
        return BlogSearchResult.builder()
                .meta(Meta.builder()
                        .searchSource(SearchSource.KAKAO)
                        .totalItems(this.meta.getPageable_count())
                        .currentPage(start)
                        .build())
                .blogPosts(this.documents.stream().map(kakaoBlogSearchDocument -> kakaoBlogSearchDocument.toDomainEntity()).collect(Collectors.toList()))
                .build();
    }
}
