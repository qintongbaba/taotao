package com.wuqinghua.taotao.rest.controller;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuqinghua on 17/11/10.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaoTaoResult getContentList(@PathVariable Long contentCategoryId) {
        return contentService.getContentList(contentCategoryId);
    }
}
