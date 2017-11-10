package com.wuqinghua.taotao.manager.mapper;

import com.wuqinghua.taotao.manager.pojo.TTItem;

import java.util.List;

/**
 * Created by wuqinghua on 17/10/19.
 */
public interface ItemMapper {


    /**
     * 新增商品
     * @param ttItem
     */
    public void insert(TTItem ttItem);

    /**
     * 所有的商品
     *
     * @return
     */
    public List<TTItem> selectAllItem();
}
