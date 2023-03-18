package com.study.blogsearch.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Meta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
