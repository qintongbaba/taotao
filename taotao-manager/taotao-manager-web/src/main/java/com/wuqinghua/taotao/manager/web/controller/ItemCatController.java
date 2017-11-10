package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;
import com.wuqinghua.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/3.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> list(Long id) {
        return itemCatService.list(id);
    }
}
