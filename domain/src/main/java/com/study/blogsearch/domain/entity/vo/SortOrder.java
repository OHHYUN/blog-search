package com.study.blogsearch.domain.entity.vo;

import com.study.blogsearch.domain.exception.BlogSearchException;
import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;

public enum SortOrder {
    ACCURACY, // 정확도순
    RECENCY; // 최신순

    public static SortOrder fromString(String sortString) {
        for (SortOrder sortOrder : SortOrder.values()) {
            if (sortOrder.name().equalsIgnoreCase(sortString)) {
                return sortOrder;
            }
        }
        throw new BlogSearchException(BlogSearchErrorCode.INVALID_SORT_CODE);
    }

    public String getSortNameForNaver() {
        switch (this) {
            case RECENCY:
                return "date";
            case ACCURACY:
            default:
                return "sim";
        }
    }
}
