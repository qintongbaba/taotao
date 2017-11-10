package com.wuqinghua.taotao.manager.pojo;

import java.util.Date;

/**
 * 商品分类规格参数
 */
public class TTItemParam {
    private Long id;

    private TTItemCat itemCat;

    private Date created;

    private Date updated;

    private String paramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TTItemCat getItemCat() {
        return itemCat;
    }

    public void setItemCat(TTItemCat itemCat) {
        this.itemCat = itemCat;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}