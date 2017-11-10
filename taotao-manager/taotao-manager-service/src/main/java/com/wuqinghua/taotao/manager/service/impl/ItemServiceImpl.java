package com.wuqinghua.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuqinghua.taotao.common.constant.ItemStatus;
import com.wuqinghua.taotao.common.pojo.EUDataGridResult;
import com.wuqinghua.taotao.common.utils.IDUtils;
import com.wuqinghua.taotao.manager.mapper.ItemDescMapper;
import com.wuqinghua.taotao.manager.mapper.ItemMapper;
import com.wuqinghua.taotao.manager.pojo.TTItem;
import com.wuqinghua.taotao.manager.pojo.TTItemCat;
import com.wuqinghua.taotao.manager.pojo.TTItemDesc;
import com.wuqinghua.taotao.manager.service.ItemService;
import com.wuqinghua.taotao.manager.vo.ItemVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuqinghua on 17/10/23.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EUDataGridResult<ItemVo> listItem(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TTItem> ttItems = itemMapper.selectAllItem();
        PageInfo<TTItem> pageInfo = new PageInfo<>(ttItems);

        EUDataGridResult<ItemVo> result = new EUDataGridResult<>();
        result.setTotal(pageInfo.getTotal());

        List<ItemVo> items = new ArrayList<>();
        for (TTItem ttItem : ttItems) {
            ItemVo itemVo = new ItemVo();
            try {
                PropertyUtils.copyProperties(itemVo, ttItem);
                //设置商品类目名称
                itemVo.setCatName(ttItem.getItemCat().getName());
                //设置商品状态
                ItemStatus status = ItemStatus.build(ttItem.getStatus());
                itemVo.setStatusDesc(status.getDesc());
            } catch (Exception e) {
                e.printStackTrace();
            }
            items.add(itemVo);
        }
        result.setRows(items);

        return result;
    }

    @Override
    public void saveItem(ItemVo itemVo) {
        itemVo.setCreated(new Date());
        itemVo.setUpdated(new Date());
        TTItem ttItem = new TTItem();
        TTItemDesc ttItemDesc = new TTItemDesc();
        try {
            PropertyUtils.copyProperties(ttItem, itemVo);
            PropertyUtils.copyProperties(ttItemDesc, itemVo);
            TTItemCat ttItemCat = new TTItemCat();
            ttItemCat.setId(itemVo.getCid());
            ttItem.setItemCat(ttItemCat);
            ttItem.setStatus(new Byte("1"));
            ttItem.setId(IDUtils.genItemId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ttItemDesc.setItemDesc(itemVo.getDesc());
        ttItemDesc.setItem(ttItem);

        itemMapper.insert(ttItem);
        itemDescMapper.insert(ttItemDesc);
    }
}
