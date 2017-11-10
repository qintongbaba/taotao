package com.wuqinghua.taotao.manager.mapper;

import com.wuqinghua.taotao.manager.pojo.TTItemCat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/3.
 */
public interface ItemCatMapper {
    List<TTItemCat> listByParentId(@Param("parentId") Long parentId);
}
