package com.wuqinghua.taotao.search.service;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by wuqinghua on 17/11/12.
 */
public interface SolrItemService {

    TaoTaoResult importAllItems();

    SearchResult searchItem(String keywords, Integer page, Integer rows);
}
