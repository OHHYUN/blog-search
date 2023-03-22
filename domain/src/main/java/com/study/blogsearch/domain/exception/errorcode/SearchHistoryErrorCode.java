package com.study.blogsearch.domain.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchHistoryErrorCode implements ErrorCode{

    DB_CONNECTION_ERROR(500, "500001", "데이터 베이스 연결 에러가 발생하였습니다."),
    DATA_FETCH_ERROR(500, "500002", "조회 중 에러가 발생하였습니다."),
    DATA_UPDATE_ERROR(500, "500003", "업데이트 중 에러가 발생하였습니다."),
    INVALID_PARAMETER(400, "400001", "잘못된 검색 기록 요청 파라미터");

    private final int status;
    private final String code;
    private final String message;


}
