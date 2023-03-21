package com.study.blogsearch.domain.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogSearchErrorCode implements ErrorCode{
    INVALID_SEARCH_SOURCE(500, "500001", "검색 소스 타입을 정의하는 데 문제가 발생하였습니다."),
    BLOG_SERVER_ERROR(500, "500002", "블로그 서버 통신 중 에러가 발생하였습니다."),
    KAKAO_BLOG_SERVER_ERROR(500, "500003", "카카오 블로그 서버 통신 중 에러가 발생하였습니다."),
    NAVER_BLOG_SERVER_ERROR(500, "500004", "네이버 블로그 서버 통신 중 에러가 발생하였습니다."),
    JSON_CONVERT_ERROR(500, "500003", "객체 변환 중 오류가 발생하였습니다."),
    INVALID_SORT_CODE(400, "400001", "정렬 입력값이 잘 못 되었습니다. 가능한 값은 ACCURACY, RECENCY 입니다.");

    private final int status;
    private final String code;
    private final String message;
}
