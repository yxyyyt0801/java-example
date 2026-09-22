package com.sciatta.java.spring.jdbc.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by yangxiaoyu on 2026/9/21<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * PageResult
 */
@Data
public class PageResult<T> {
    private List<T> list;       // 当前页数据
    private long total;         // 总记录数
    private long pageNum;        // 当前页码
    private long pageSize;       // 每页条数
    private long totalPages;     // 总页数

    public PageResult(List<T> list, long total, long pageNum, long pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPages = pageSize == 0 ? 0
                : (int) Math.ceil((double) total / pageSize);
    }
}
