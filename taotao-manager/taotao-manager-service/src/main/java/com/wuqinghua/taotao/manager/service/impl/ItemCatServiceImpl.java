package com.wuqinghua.taotao.manager.service.impl;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;
import com.wuqinghua.taotao.manager.mapper.ItemCatMapper;
import com.wuqinghua.taotao.manager.pojo.TTItemCat;
import com.wuqinghua.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/3.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> list(Long id) {

        List<TTItemCat> ttItemCats = itemCatMapper.listByParentId(id);

        List<EUTreeNode> treeNodes = new ArrayList<>();
        for (TTItemCat itemCat : ttItemCats) {
            EUTreeNode treeNode = new EUTreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() ? "closed" : "open");
            treeNodes.add(treeNode);
        }

        return treeNodes;
    }
}
