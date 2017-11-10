package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.pojo.TTContent;
import com.wuqinghua.taotao.manager.pojo.TTContentCategory;
import com.wuqinghua.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuqinghua on 17/11/9.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public EUDataGridResult<TTContent> list(Long categroyId, Integer page, Integer rows) {
        return contentService.list(categroyId, page, rows);
    }

    @RequestMapping("/save")
    @ResponseBody
    public TaoTaoResult save(Long categoryId,TTContent content){


        TTContentCategory contentCategory = new TTContentCategory();
        contentCategory.setId(categoryId);
        content.setCategory(contentCategory);



        return contentService.save(content);
    }
}
