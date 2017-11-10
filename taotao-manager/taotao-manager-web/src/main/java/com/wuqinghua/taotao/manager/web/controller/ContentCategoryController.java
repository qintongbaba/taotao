package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.service.ContentCategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/8.
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategroyService contentCategroyService;

    @GetMapping("/list")
    @ResponseBody
    public List<EUTreeNode> list(Long id) {
        return contentCategroyService.list(id);
    }


    @PostMapping("/create")
    @ResponseBody
    public TaoTaoResult create(Long parentId, String name) {
        return contentCategroyService.create(parentId, name);
    }

    @PostMapping("/rename")
    @ResponseBody
    public TaoTaoResult rename(Long id, String newName) {
        return contentCategroyService.rename(id, newName);
    }

    @PostMapping("/delete")
    @ResponseBody
    public TaoTaoResult delete(Long id) {
        return contentCategroyService.delete(id);
    }
}
