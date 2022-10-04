package com.cle.pojo;

import java.util.List;

/**
 * @author kele
 */
public class PageBean<T> {
    //总计记录
    private int totalCount;
    //当前页数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
