package com.study.blogsearch.domain.exception;

import com.study.blogsearch.domain.exception.errorcode.BlogSearchErrorCode;

public class BlogSearchException extends GlobalException{
    public BlogSearchException(BlogSearchErrorCode errorCode) {
        super(errorCode);
    }
}
