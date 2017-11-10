package com.wuqinghua.taotao.manager.web.controller;

import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.service.ItemParamService;
import com.wuqinghua.taotao.manager.vo.ItemParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/4.
 */
@Controller
@RequestMapping("/param/temp")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult list(Integer page, Integer rows) {
        return itemParamService.listItemParam(page, rows);
    }

    @PostMapping("/getByCatId/{catId}")
    @ResponseBody
    public TaoTaoResult getByCatId(@PathVariable Long catId) {
        ItemParamVo itemParamVo = itemParamService.getByCatId(catId);
        return TaoTaoResult.defaultOK(itemParamVo);
    }

    @PostMapping("/save/{catId}")
    @ResponseBody
    public TaoTaoResult saveParam(@PathVariable Long catId, String paramData) {
        itemParamService.save(paramData, catId);
        return TaoTaoResult.defaultOK();
    }
}
