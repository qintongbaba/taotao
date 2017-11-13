package com.wuqinghua.taotao.search.controller;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuqinghua on 17/11/12.
 */
@Controller
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private SolrItemService solrItemService;

    @RequestMapping("/importItems")
    @ResponseBody
    public TaoTaoResult importImtes() {
        return solrItemService.importAllItems();
    }
}
