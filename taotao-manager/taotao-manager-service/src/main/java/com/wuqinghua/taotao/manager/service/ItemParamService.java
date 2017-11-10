package com.wuqinghua.taotao.manager.service;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.manager.vo.ItemParamVo;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/4.
 */
public interface ItemParamService {
    public EUDataGridResult listItemParam(Integer page, Integer rows);


    /**
     * 根据类目id获取 规格参数
     * @param catId
     * @return
     */
    public ItemParamVo getByCatId(Long catId);


    /**
     * 保存规格参数模板
     * @param paramData
     * @param catId
     */
    public void save(String paramData,Long catId);
}
