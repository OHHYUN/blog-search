package com.study.blogsearch.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class KakaoBlogSearchMeta {
    private int total_count;
    private int pageable_count;
    private boolean is_end;
}
