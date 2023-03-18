package com.study.blogsearch.infrastructure.dto;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.Meta;
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

    public BlogSearchResult toDomainEntity() {
        return BlogSearchResult.builder()
                .meta(new Meta(this.meta.getTotal_count(), this.meta.getPageable_count(), this.meta.is_end()))
                .blogPosts(this.documents.stream().map(kakaoBlogSearchDocument -> kakaoBlogSearchDocument.toDomainEntity()).collect(Collectors.toList()))
                .build();
    }
}
