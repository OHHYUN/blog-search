package com.study.blogsearch.infrastructure.dto;

import com.study.blogsearch.domain.entity.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class KakaoBlogSearchDocument {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    public BlogPost toDomainEntity() {
        return BlogPost.builder()
                .postTitle(this.title)
                .postContent(this.contents)
                .postUrl(this.url)
                .blogName(this.blogname)
                .postThumbnail(this.thumbnail)
                .postDatetime(LocalDateTime.parse(this.datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
    }


}
