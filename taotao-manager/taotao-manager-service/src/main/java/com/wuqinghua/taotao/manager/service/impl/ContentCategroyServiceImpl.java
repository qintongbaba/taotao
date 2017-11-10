package com.wuqinghua.taotao.manager.service.impl;

import com.wuqinghua.taotao.common.pojo.EUTreeNode;
import com.wuqinghua.taotao.common.pojo.TaoTaoResult;
import com.wuqinghua.taotao.manager.mapper.ContentCategoryMapper;
import com.wuqinghua.taotao.manager.pojo.TTContentCategory;
import com.wuqinghua.taotao.manager.service.ContentCategroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/8.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContentCategroyServiceImpl implements ContentCategroyService {


    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> list(Long parentId) {
        List<TTContentCategory> categorys = contentCategoryMapper.selectByParent(parentId);
        List<EUTreeNode> treeNodes = new ArrayList<>();
        for (TTContentCategory contentCategory : categorys) {
            EUTreeNode node = new EUTreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent() ? "closed" : "open");
            treeNodes.add(node);
        }

        return treeNodes;
    }

    @Override
    public TaoTaoResult create(Long parentId, String name) {
        TTContentCategory contentCategory = new TTContentCategory();
        contentCategory.setName(name);
        contentCategory.setCreated(new Date());
        contentCategory.setIsParent(false);
        TTContentCategory parent = new TTContentCategory();
        parent.setId(parentId);
        contentCategory.setParent(parent);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);

        //将其父节点isParent变为1
        parent.setIsParent(true);
        parent.setUpdated(new Date());
        contentCategoryMapper.update(parent);

        TaoTaoResult result = TaoTaoResult.defaultOK(contentCategory);
        return result;
    }

    @Override
    public TaoTaoResult rename(Long id, String newName) {
        TTContentCategory contentCategory = new TTContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(newName);
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.update(contentCategory);
        return TaoTaoResult.defaultOK();
    }

    @Override
    public TaoTaoResult delete(Long id) {
        List<TTContentCategory> ttContentCategories = contentCategoryMapper.selectByParent(id);
        if (ttContentCategories != null && ttContentCategories.size() > 0) {
            return TaoTaoResult.defaultFaild("该分类下还存在分类，不能删除!");
        }
        //查询该分类的父分类
        TTContentCategory contentCategory = contentCategoryMapper.selectById(id);

        contentCategoryMapper.deleteById(id);
        if (contentCategory.getParent() != null) {
            List<TTContentCategory> contentCategories = contentCategoryMapper.selectByParent
                    (contentCategory.getParent().getId());
            if (contentCategories == null || contentCategories.size() <= 0) {
                //将该父分类的is_parent修改为0
                contentCategory.getParent().setIsParent(false);
                contentCategoryMapper.update(contentCategory.getParent());
            }
        }
        return TaoTaoResult.defaultOK();
    }
}
