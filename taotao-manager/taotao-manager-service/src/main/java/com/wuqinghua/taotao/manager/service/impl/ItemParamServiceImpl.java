package com.wuqinghua.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.manager.mapper.ItemParamMapper;
import com.wuqinghua.taotao.manager.pojo.TTItemCat;
import com.wuqinghua.taotao.manager.pojo.TTItemParam;
import com.wuqinghua.taotao.manager.service.ItemParamService;
import com.wuqinghua.taotao.manager.vo.ItemParamVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuqinghua on 17/11/4.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private ItemParamMapper itemParamMapper;

    @Override
    public EUDataGridResult listItemParam(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TTItemParam> ttItemParams = itemParamMapper.selectAll();
        List<ItemParamVo> itemParamVos = new ArrayList<>();
        for (TTItemParam ttItemParam : ttItemParams) {
            ItemParamVo itemParamVo = new ItemParamVo();
            try {
                PropertyUtils.copyProperties(itemParamVo, ttItemParam);
                PropertyUtils.setProperty(itemParamVo, "itemCatId", ttItemParam.getItemCat().getId());
                PropertyUtils.setProperty(itemParamVo, "itemCatName", ttItemParam.getItemCat().getName());
                itemParamVos.add(itemParamVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PageInfo<TTItemParam> pageInfo = new PageInfo<TTItemParam>(ttItemParams);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(itemParamVos);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public ItemParamVo getByCatId(Long catId) {
        TTItemParam ttItemParam = itemParamMapper.selectByCatId(catId);
        ItemParamVo itemParamVo = null;
        if (ttItemParam != null) {
            itemParamVo = new ItemParamVo();
            try {
                PropertyUtils.copyProperties(itemParamVo, ttItemParam);
                PropertyUtils.setProperty(itemParamVo, "itemCatId", ttItemParam.getItemCat().getId());
                PropertyUtils.setProperty(itemParamVo, "itemCatName", ttItemParam.getItemCat().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return itemParamVo;
    }

    @Override
    public void save(String paramData, Long catId) {
        TTItemParam itemParam = new TTItemParam();
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        TTItemCat itemCat = new TTItemCat();
        itemCat.setId(catId);
        itemParam.setItemCat(itemCat);
        itemParam.setParamData(paramData);
        itemParamMapper.insert(itemParam);
    }
}
