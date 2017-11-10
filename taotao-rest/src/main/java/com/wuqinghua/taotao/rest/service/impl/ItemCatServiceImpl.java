package com.wuqinghua.taotao.rest.service.impl;

import com.wuqinghua.taotao.manager.mapper.ItemCatMapper;
import com.wuqinghua.taotao.manager.pojo.TTItemCat;
import com.wuqinghua.taotao.rest.pojo.CatNode;
import com.wuqinghua.taotao.rest.pojo.CatResult;
import com.wuqinghua.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/5.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public CatResult getItemCat() {
        CatResult result = new CatResult();
        result.setData(getItemCatList(null));
        return result;
    }

    private List<?> getItemCatList(Long parentId) {
        List<TTItemCat> ttItemCats = itemCatMapper.listByParentId(parentId);
        List resultList = new ArrayList<>();

        for (TTItemCat itemCat : ttItemCats) {

            if (itemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == null) {
                    catNode.setName("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                } else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setUrl("/products/" + itemCat.getId() + ".html");
                catNode.setItem(getItemCatList(itemCat.getId()));
                resultList.add(catNode);
            } else {
                resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
            }
        }
        return resultList;
    }
}
