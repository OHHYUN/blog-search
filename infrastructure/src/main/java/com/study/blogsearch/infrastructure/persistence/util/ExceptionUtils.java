package com.study.blogsearch.infrastructure.persistence.util;

import com.study.blogsearch.domain.exception.SearchHistoryException;
import com.study.blogsearch.domain.exception.errorcode.SearchHistoryErrorCode;

import javax.persistence.PersistenceException;
import java.util.function.Supplier;

public class ExceptionUtils {
    private ExceptionUtils() {}
    public static <T> T handleDbExceptions(Supplier<T> supplier, SearchHistoryErrorCode errorCode) {
        try {
            return supplier.get();
        } catch (PersistenceException e) {
            throw new SearchHistoryException(SearchHistoryErrorCode.DB_CONNECTION_ERROR);
        } catch (Exception e) {
            throw new SearchHistoryException(errorCode);
        }
    }
}
