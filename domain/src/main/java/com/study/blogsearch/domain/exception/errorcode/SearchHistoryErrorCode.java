package com.study.blogsearch.domain.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchHistoryErrorCode implements ErrorCode{
    INVALID_PARAMETER(400, "ERR400", "잘못된 검색 기록 요청 파라미터");

    private final int status;
    private final String code;
    private final String message;


}
