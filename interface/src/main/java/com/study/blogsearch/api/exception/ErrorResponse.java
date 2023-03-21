package com.study.blogsearch.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResponseEntity<ErrorResponse> toResponseEntity() {
        return new ResponseEntity<>(this, HttpStatus.valueOf(this.status));
    }
}
