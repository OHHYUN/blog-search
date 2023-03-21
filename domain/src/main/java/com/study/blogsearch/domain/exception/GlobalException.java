package com.study.blogsearch.domain.exception;

import com.study.blogsearch.domain.exception.errorcode.ErrorCode;

public class GlobalException extends RuntimeException{
    private final ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
