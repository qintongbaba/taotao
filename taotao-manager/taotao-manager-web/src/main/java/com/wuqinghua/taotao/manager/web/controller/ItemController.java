package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.service.ItemService;
import com.wuqinghua.taotao.manager.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wuqinghua on 17/10/20.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult<ItemVo> list(Integer page, Integer rows) {
        EUDataGridResult<ItemVo> result = itemService.listItem(page, rows);
        return result;
    }


    @PostMapping("/save")
    @ResponseBody
    public TaoTaoResult save(@RequestBody ItemVo itemVo) {
        itemService.saveItem(itemVo);
        return TaoTaoResult.defaultOK();
    }

}
