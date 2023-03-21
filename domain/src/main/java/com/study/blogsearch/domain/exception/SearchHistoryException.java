package com.study.blogsearch.domain.exception;

import com.study.blogsearch.domain.exception.errorcode.SearchHistoryErrorCode;

public class SearchHistoryException extends GlobalException{
    public SearchHistoryException(SearchHistoryErrorCode errorCode) {
        super(errorCode);
    }
}
