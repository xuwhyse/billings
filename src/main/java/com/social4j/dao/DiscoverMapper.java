package com.social4j.dao;

import com.social4j.model.Discover;
import com.social4j.model.DiscoverCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DiscoverMapper {
    int countByExample(DiscoverCriteria example);

    int deleteByExample(DiscoverCriteria example);

    int deleteByPrimaryKey(Integer discoverId);

    int insert(Discover record);

    int insertSelective(Discover record);

    List<Discover> selectByExampleWithRowbounds(DiscoverCriteria example, RowBounds rowBounds);

    List<Discover> selectByExample(DiscoverCriteria example);

    Discover selectByPrimaryKey(Integer discoverId);

    int updateByExampleSelective(@Param("record") Discover record, @Param("example") DiscoverCriteria example);

    int updateByExample(@Param("record") Discover record, @Param("example") DiscoverCriteria example);

    int updateByPrimaryKeySelective(Discover record);

    int updateByPrimaryKey(Discover record);
}