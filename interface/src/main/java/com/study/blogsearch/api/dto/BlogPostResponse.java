package com.study.blogsearch.api.dto;

import com.study.blogsearch.domain.entity.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class BlogPostResponse {

    private String title;
    private String content;
    private String url;
    private String blogName;
    private String postThumbnail;
    private LocalDateTime postDateTime;

    public static BlogPostResponse from(BlogPost post) {
        return BlogPostResponse.builder()
                .title(post.getPostTitle())
                .content(post.getPostContent())
                .url(post.getPostUrl())
                .blogName(post.getBlogName())
                .postThumbnail(post.getPostThumbnail())
                .postDateTime(post.getPostDatetime())
                .build();
    }

}
