package com.study.blogsearch.infrastructure.serverapi.dto;

import com.study.blogsearch.domain.entity.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class NaverBlogSearchItem {

    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;

    public BlogPost toDomainEntity() {
        LocalDateTime postDatetime = LocalDate.parse(postdate, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();

        return BlogPost.builder()
                .postTitle(this.title)
                .postContent(this.description)
                .postUrl(this.bloggerlink)
                .blogName(this.bloggername)
                .postDatetime(postDatetime)
                .build();
    }
}
