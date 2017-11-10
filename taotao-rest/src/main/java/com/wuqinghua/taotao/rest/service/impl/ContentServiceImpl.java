package com.wuqinghua.taotao.rest.service.impl;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.mapper.ContentMapper;
import com.wuqinghua.taotao.manager.pojo.TTContent;
import com.wuqinghua.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/10.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public TaoTaoResult getContentList(long categoryId) {
        List<TTContent> contentList = contentMapper.listByCategroyId(categoryId);
        return TaoTaoResult.defaultOK(contentList);
    }
}
