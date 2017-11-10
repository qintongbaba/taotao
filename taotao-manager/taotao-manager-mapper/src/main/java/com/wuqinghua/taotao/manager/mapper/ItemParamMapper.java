package com.wuqinghua.taotao.manager.mapper;

import com.wuqinghua.taotao.manager.pojo.TTItemParam;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/4.
 */
public interface ItemParamMapper {
    public List<TTItemParam> selectAll();


    public TTItemParam selectByCatId(Long catId);


    public void insert(TTItemParam itemParam);
}
