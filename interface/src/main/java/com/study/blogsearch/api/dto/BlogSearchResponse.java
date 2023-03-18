package com.study.blogsearch.api.dto;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class BlogSearchResponse {

    private SearchMeta meta;
    private List<BlogPostResponse> posts;

    public static BlogSearchResponse of(BlogSearchResult domain, int start) {
        Meta metaData = domain.getMeta();

        return BlogSearchResponse.builder()
                .meta(new SearchMeta(metaData.getTotal_count(), start))
                .posts(domain.getBlogPosts().stream().map(BlogPostResponse::from).collect(Collectors.toList()))
                .build();
    }

}
