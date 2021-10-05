package com.example.ex5_db_springboot.model;

public class Metadata {
    private Long total;
    private int page;
    private int limit;

    public Metadata(Long total, int page, int limit) {
        this.total = total;
        this.page = page;
        this.limit = limit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
