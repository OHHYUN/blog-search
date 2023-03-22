package com.study.blogsearch.api.controller;

import com.study.blogsearch.api.dto.BlogPostResponse;
import com.study.blogsearch.api.dto.PaginationResponse;
import com.study.blogsearch.application.command.BlogSearchQueryCommand;
import com.study.blogsearch.application.usecase.BlogSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class BlogSearchController {

    private final BlogSearchUseCase blogSearchUseCase;

    @GetMapping(value = "/blog", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse<BlogPostResponse> searchBlog(
            @RequestParam String query,
            @RequestParam(required = false, defaultValue = "RECENCY") String sort,
            @RequestParam int start
    ) {
        final var blogSearchResult = blogSearchUseCase.searchBlog(BlogSearchQueryCommand.of(query, sort, start));
        return PaginationResponse.fromBlogSearchResult(blogSearchResult);
    }
}
