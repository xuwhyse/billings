package com.hundsun.internet.billingsystem.dao;

import com.hundsun.internet.billingsystem.model.WeekSignDescBean;
import com.hundsun.internet.billingsystem.model.WeekSignDescBeanCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WeekSignDescBeanMapper {
    int countByExample(WeekSignDescBeanCriteria example);

    int deleteByExample(WeekSignDescBeanCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(WeekSignDescBean record);

    int insertSelective(WeekSignDescBean record);

    List<WeekSignDescBean> selectByExampleWithRowbounds(WeekSignDescBeanCriteria example, RowBounds rowBounds);

    List<WeekSignDescBean> selectByExample(WeekSignDescBeanCriteria example);

    WeekSignDescBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WeekSignDescBean record, @Param("example") WeekSignDescBeanCriteria example);

    int updateByExample(@Param("record") WeekSignDescBean record, @Param("example") WeekSignDescBeanCriteria example);

    int updateByPrimaryKeySelective(WeekSignDescBean record);

    int updateByPrimaryKey(WeekSignDescBean record);
}