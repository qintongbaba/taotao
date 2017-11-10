package com.wuqinghua.taotao.manager.pojo;

import java.util.Date;

/**
 * 商品描述
 */
public class TTItemDesc {
    private Long id;

    private TTItem item;

    private Date created;

    private Date updated;

    private String itemDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TTItem getItem() {
        return item;
    }

    public void setItem(TTItem item) {
        this.item = item;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}