package com.study.blogsearch.api.exception;

import com.study.blogsearch.domain.exception.GlobalException;
import com.study.blogsearch.domain.exception.errorcode.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception e) {
        return Map.of(
                "code", "500",
                "message", "알 수 없는 오류가 발생 하였습니다."
        );
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleException(GlobalException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        return errorResponse.toResponseEntity();
    }

}
