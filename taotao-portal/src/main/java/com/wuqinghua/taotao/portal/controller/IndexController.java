package com.wuqinghua.taotao.portal.controller;

import com.wuqinghua.taotao.common.utils.JsonUtil;
import com.wuqinghua.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wuqinghua on 17/11/5.
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("ad1", JsonUtil.objectToJson(contentService.listIndexAdContent()));
        return "index";
    }
}
