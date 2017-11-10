package com.wuqinghua.taotao.manager.service;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/8.
 */
public interface ContentCategroyService {

    public List<EUTreeNode> list(Long parentId);


    public TaoTaoResult create(Long parentId,String name);

    public TaoTaoResult rename(Long id,String newName);

    public TaoTaoResult delete(Long id);
}
