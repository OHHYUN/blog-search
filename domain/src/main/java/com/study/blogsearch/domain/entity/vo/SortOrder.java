package com.study.blogsearch.domain.entity.vo;

public enum SortOrder {
    ACCURACY, // 정확도순
    RECENCY; // 최신순

    public static SortOrder fromString(String sortString) {
        for (SortOrder sortOrder : SortOrder.values()) {
            if (sortOrder.name().equalsIgnoreCase(sortString)) {
                return sortOrder;
            }
        }
        throw new IllegalArgumentException("잘못된 값입니다.");
    }
}
