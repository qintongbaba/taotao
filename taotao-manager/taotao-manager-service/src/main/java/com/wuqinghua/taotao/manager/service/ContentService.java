package com.wuqinghua.taotao.manager.service;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.pojo.TTContent;

/**
 * Created by wuqinghua on 17/11/9.
 */
public interface ContentService {

    public EUDataGridResult<TTContent> list(Long categroyId, Integer page, Integer rows);


    public TaoTaoResult save(TTContent content);
}
