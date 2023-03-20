package com.study.blogsearch.domain.entity;

import com.study.blogsearch.domain.entity.vo.Meta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class BlogSearchResult {
    private Meta meta;
    private List<BlogPost> blogPosts;
}
