package com.wuqinghua.taotao.common.pojo;

import java.util.List;

/**
 * Created by wuqinghua on 17/10/20.
 */
public class EUDataGridResult<T> {

    private long total;

    public List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
