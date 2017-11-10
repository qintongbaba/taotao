package com.wuqinghua.taotao.manager.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wuqinghua on 17/10/19.
 * 查看界面的Controller
 */
@Controller
@RequestMapping("/show")
public class PageController {

    @RequestMapping("/{pageName}")
    public String showPage(@PathVariable String pageName) {
        return pageName;
    }
}
