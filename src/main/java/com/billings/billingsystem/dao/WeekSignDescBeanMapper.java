package com.billings.billingsystem.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.billings.billingsystem.model.WeekSignDescBean;
import com.billings.billingsystem.model.WeekSignDescBeanCriteria;

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