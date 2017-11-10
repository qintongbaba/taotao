package com.wuqinghua.taotao.rest.controller;

import com.wuqinghua.taotao.manager.mapper.ItemCatMapper;
import com.wuqinghua.taotao.rest.pojo.CatResult;
import com.wuqinghua.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuqinghua on 17/11/5.
 */

@Controller
@RequestMapping("/itemcat")
public class ItemCatController {


    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult itemCat = itemCatService.getItemCat();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCat);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
