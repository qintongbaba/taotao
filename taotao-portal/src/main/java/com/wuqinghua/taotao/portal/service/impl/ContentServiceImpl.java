package com.wuqinghua.taotao.portal.service.impl;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.common.utils.HttpClientUtil;
import com.wuqinghua.taotao.manager.pojo.TTContent;
import com.wuqinghua.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuqinghua on 17/11/10.
 */
@Service
public class ContentServiceImpl implements ContentService {


    @Value("${rest_base_url}")
    private String restBaseUrl;

    @Value("${rest_index_ad_url}")
    private String indexAdUrl;

    @Override
    public List<Map> listIndexAdContent() {
        String response = HttpClientUtil.doGet(restBaseUrl + indexAdUrl);
        TaoTaoResult result = TaoTaoResult.format(response, TTContent.class);
        if (result != null) {
            List<TTContent> contentList = (List<TTContent>) result.getData();
            List<Map> contents = new ArrayList<>();
            for (TTContent content : contentList) {
                Map map = new HashMap();
                map.put("alt", "");
                map.put("height", 240);
                map.put("heightB", 240);
                map.put("href", content.getUrl());
                map.put("src", content.getPic());
                map.put("srcB", content.getPic2());
                map.put("width", 670);
                map.put("widthB", 550);
                contents.add(map);
            }
            return contents;

        }
        return null;
    }
}
