package com.study.blogsearch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class BlogPost {
    private String postTitle;
    private String postContent;
    private String postUrl;
    private String blogName;
    @Builder.Default
    private String postThumbnail = "";
    private LocalDateTime postDatetime;
}
