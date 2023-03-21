package com.study.blogsearch.domain.entity.vo;

import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Meta {
    private SearchSource searchSource;
    private long totalItems;
    private int itemsPerPage;
    private int currentPage;

    public int getItemPerPage() {
        switch (searchSource) {
            case KAKAO:
                return 10;
            case NAVER:
                return 20;
            default:
                throw new BlogSearchException(BlogSearchErrorCode.INVALID_SEARCH_SOURCE);
        }
    }

}
