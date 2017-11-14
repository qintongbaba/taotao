package com.wuqinghua.taotao.search.pojo;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/14.
 */
public class SearchResult {

    private List<ItemVo> itemList;

    private Long recordCount;//总记录数

    private Long pageCount;//总页数

    private Long curPage; //当前页


    public List<ItemVo> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemVo> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }
}
