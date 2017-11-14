package com.wuqinghua.taotao.search.service.impl;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.search.mapper.ItemMapper;
import com.wuqinghua.taotao.search.pojo.ItemVo;
import com.wuqinghua.taotao.search.pojo.SearchResult;
import com.wuqinghua.taotao.search.service.SolrItemService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/12.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SolrItemServiceImpl implements SolrItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public TaoTaoResult importAllItems() {

        //获取所有的产品信息
        List<ItemVo> itemList = itemMapper.getItemList();
        if (!CollectionUtils.isEmpty(itemList)) {
            try {
                //将其添加到solr库中
                for (ItemVo itemVo : itemList) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.setField("id", itemVo.getItemId());
                    document.setField("item_title", itemVo.getItemTitle());
                    document.setField("item_sell_point", itemVo.getItemSellPoint());
                    document.setField("item_price", itemVo.getItemPrice());
                    document.setField("item_image", itemVo.getItemImage());
                    document.setField("item_category_name", itemVo.getItemCategoryName());
                    document.setField("item_desc", itemVo.getItemDesc());
                    solrClient.add(document);
                }
                solrClient.commit();
            } catch (Exception e) {
                e.printStackTrace();
                return TaoTaoResult.defaultFaild("添加产品到solr库错误!");
            }
        }
        return TaoTaoResult.defaultOK();
    }

    @Override
    public SearchResult searchItem(String keywords, Integer page, Integer rows) {

        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery(keywords);
        //设置分页
        query.setStart((page - 1) * rows);
        query.setRows(rows);

        //设置默认搜索域
        query.set("df", "item_keywords");

        //设置高亮
//        query.setHighlight(true);
//        query.setHighlightSimplePre("<em style=\"color:red;\">");
//        query.setHighlightSimplePost("</em>");
        SearchResult result = new SearchResult();
        result.setCurPage(page.longValue());
        result.setPageCount(rows.longValue());
        try {
            QueryResponse queryResponse = solrClient.query(query);
            SolrDocumentList results = queryResponse.getResults();
            result.setRecordCount(results.getNumFound());
            List<ItemVo> items = new ArrayList<>();
            for (SolrDocument solrDocument : results) {
                ItemVo itemVo = new ItemVo();
                itemVo.setItemCategoryName((String) solrDocument.get("item_category_name"));
                itemVo.setItemDesc((String) solrDocument.get("item_desc"));
                itemVo.setItemId((String) solrDocument.get("id"));
                itemVo.setItemImage((String) solrDocument.get("item_image"));
                itemVo.setItemPrice((Long) solrDocument.get("item_price"));
                itemVo.setItemSellPoint((String) solrDocument.get("item_sell_point"));
                itemVo.setItemTitle((String) solrDocument.get("item_title"));
                items.add(itemVo);
            }
            result.setItemList(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
