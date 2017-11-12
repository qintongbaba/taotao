package com.wuqinghua.taotao.rest.service.impl;

import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.common.utils.JedisManagerUtils;
import com.wuqinghua.taotao.common.utils.JsonUtil;
import com.wuqinghua.taotao.manager.mapper.ContentMapper;
import com.wuqinghua.taotao.manager.pojo.TTContent;
import com.wuqinghua.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/10.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public TaoTaoResult getContentList(long categoryId) {
        List<TTContent> contentList = null;
        try {
            //获取缓存
            byte[] bytes = JedisManagerUtils.get(JedisManagerUtils.PREFIX + ":contents");
            String jsonStr = new String(bytes);
            contentList = JsonUtil.jsonToList(jsonStr, TTContent.class);
        } catch (Exception e) {
            e.printStackTrace();

        }

        if (contentList == null) {
            contentList = contentMapper.listByCategroyId(categoryId);
            try {
                //存储到缓存中
                String json = JsonUtil.objectToJson(contentList);
                JedisManagerUtils.set(JedisManagerUtils.PREFIX + ":contents", json);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return TaoTaoResult.defaultOK(contentList);
    }
}
