package com.wuqinghua.taotao.manager.service;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/3.
 */
public interface ItemCatService {
    List<EUTreeNode> list(Long id);
}
