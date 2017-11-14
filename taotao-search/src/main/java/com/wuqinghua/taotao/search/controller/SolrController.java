package com.wuqinghua.taotao.search.controller;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.search.pojo.SearchResult;
import com.wuqinghua.taotao.search.service.SolrItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuqinghua on 17/11/12.
 */
@Controller
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private SolrItemService solrItemService;

    /**
     * 导入所有的商品数据
     *
     * @return
     */
    @RequestMapping("/importItems")
    @ResponseBody
    public TaoTaoResult importImtes() {
        return solrItemService.importAllItems();
    }


    @RequestMapping("/searchItem")
    @ResponseBody
    public SearchResult searchItem(@RequestParam(value = "q") String keywords,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows", defaultValue = "60")Integer rows) {
        return solrItemService.searchItem(keywords, page, rows);
    }
}
