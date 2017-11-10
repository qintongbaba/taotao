package com.wuqinghua.taotao.manager.service;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.manager.pojo.TTItem;
import com.wuqinghua.taotao.manager.vo.ItemVo;

import java.util.List;

/**
 * Created by wuqinghua on 17/10/23.
 */
public interface ItemService {
    EUDataGridResult<ItemVo> listItem(Integer page, Integer rows);


    void saveItem(ItemVo itemVo);
}
