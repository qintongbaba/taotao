package com.wuqinghua.taotao.search.service;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 * Created by wuqinghua on 17/11/12.
 */
public interface SolrItemService {

    TaoTaoResult importAllItems();
}
