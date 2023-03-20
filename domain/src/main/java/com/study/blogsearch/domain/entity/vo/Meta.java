package com.study.blogsearch.domain.entity.vo;

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
                throw new IllegalArgumentException("일치하는 갯수가 없어용");
        }
    }

}
