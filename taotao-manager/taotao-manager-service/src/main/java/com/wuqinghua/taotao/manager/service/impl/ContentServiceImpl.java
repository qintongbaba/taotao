package com.wuqinghua.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.mapper.ContentMapper;
import com.wuqinghua.taotao.manager.pojo.TTContent;
import com.wuqinghua.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/9.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public EUDataGridResult<TTContent> list(Long categroyId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TTContent> ttContents = contentMapper.listByCategroyId(categroyId);

        EUDataGridResult<TTContent> result = new EUDataGridResult<>();
        result.setRows(ttContents);

        PageInfo<TTContent> pageInfo = new PageInfo<>(ttContents);
        result.setTotal(pageInfo.getTotal());

        return result;
    }

    @Override
    public TaoTaoResult save(TTContent content) {
        content.setUpdated(new Date());
        content.setCreated(new Date());
        contentMapper.insert(content);
        return TaoTaoResult.defaultOK();
    }
}
