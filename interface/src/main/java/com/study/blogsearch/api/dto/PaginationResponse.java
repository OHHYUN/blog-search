package com.study.blogsearch.api.dto;

import com.study.blogsearch.domain.entity.BlogSearchResult;
import com.study.blogsearch.domain.entity.vo.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class PaginationResponse<T> {

    private int currentPage;
    private int itemsPerPage;
    private long totalItems;
    private int totalPages;
    private List<T> items;

    public static PaginationResponse<BlogPostResponse> fromBlogSearchResult(BlogSearchResult domain) {
        Meta metaData = domain.getMeta();

        return PaginationResponse.<BlogPostResponse>builder()
                .currentPage(metaData.getCurrentPage())
                .itemsPerPage(metaData.getItemPerPage())
                .totalItems(metaData.getTotalItems())
                .totalPages((int) Math.ceil((double) metaData.getTotalItems() / metaData.getItemPerPage()))
                .items(domain.getBlogPosts().stream().map(BlogPostResponse::from).collect(Collectors.toList()))
                .build();
    }
}
