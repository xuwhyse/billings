package com.social4j.dao;

import com.social4j.model.DiscoverItem;
import com.social4j.model.DiscoverItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DiscoverItemMapper {
    int countByExample(DiscoverItemCriteria example);

    int deleteByExample(DiscoverItemCriteria example);

    int deleteByPrimaryKey(Integer discoverItemId);

    int insert(DiscoverItem record);

    int insertSelective(DiscoverItem record);

    List<DiscoverItem> selectByExampleWithRowbounds(DiscoverItemCriteria example, RowBounds rowBounds);

    List<DiscoverItem> selectByExample(DiscoverItemCriteria example);

    DiscoverItem selectByPrimaryKey(Integer discoverItemId);

    int updateByExampleSelective(@Param("record") DiscoverItem record, @Param("example") DiscoverItemCriteria example);

    int updateByExample(@Param("record") DiscoverItem record, @Param("example") DiscoverItemCriteria example);

    int updateByPrimaryKeySelective(DiscoverItem record);

    int updateByPrimaryKey(DiscoverItem record);
}