package com.study.blogsearch.api.dto;

import java.util.List;

public class PaginationResponse<T> {

    private int currentPage;
    private int itemsPerPage;
    private long totalItems;
    private int totalPages;
    private List<T> items;

    public PaginationResponse(int currentPage, int itemsPerPage, long totalItems, List<T> items) {
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        this.items = items;
    }
}
