package com.utils;

import com.condition.UsersCondition;

/**
 * @author Aiden
 * @version 1.0
 * @description 工具类PageUtils 封装了页码page和此页记录条数rows
 * @date 2019-12-21 11:55:33
 */
public class PageUtils extends UsersCondition {
    private Integer page;
    private Integer rows;

    public PageUtils() {
    }

    public PageUtils(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageUtils{" +
                "page=" + page +
                ", rows=" + rows +
                '}';
    }
}
