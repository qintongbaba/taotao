package com.wuqinghua.taotao.manager.mapper;

import com.wuqinghua.taotao.manager.pojo.TTContent;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/9.
 */
public interface ContentMapper {


    public List<TTContent> listByCategroyId(Long categroyId);

    void insert(TTContent content);
}
