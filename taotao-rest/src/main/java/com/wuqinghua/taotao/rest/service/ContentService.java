package com.wuqinghua.taotao.rest.service;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.pojo.TTContent;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/10.
 */
public interface ContentService {

    public TaoTaoResult getContentList(long categoryId);
}
