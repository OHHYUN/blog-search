package com.study.blogsearch.domain.exception.errorcode;

import java.io.Serializable;

public interface ErrorCode extends Serializable {
    int getStatus();
    String getCode();
    String getMessage();
}
