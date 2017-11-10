package com.wuqinghua.taotao.manager.mapper;

import com.wuqinghua.taotao.manager.pojo.TTContentCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wuqinghua on 17/11/8.
 */
public interface ContentCategoryMapper {

    /**
     * 根据父节点的ID查询内容分类
     *
     * @param parentId
     * @return
     */
    public List<TTContentCategory> selectByParent(@Param("parentId") Long parentId);


    public Long insert(TTContentCategory contentCategory);


    public void update(TTContentCategory contentCategory);

    public void deleteById(Long id);


    public TTContentCategory selectById(Long id);


}
