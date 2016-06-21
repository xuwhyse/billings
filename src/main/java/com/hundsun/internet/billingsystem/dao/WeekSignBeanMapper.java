package com.hundsun.internet.billingsystem.dao;

import com.hundsun.internet.billingsystem.model.WeekSignBean;
import com.hundsun.internet.billingsystem.model.WeekSignBeanCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WeekSignBeanMapper {
    int countByExample(WeekSignBeanCriteria example);

    int deleteByExample(WeekSignBeanCriteria example);

    int insert(WeekSignBean record);

    int insertSelective(WeekSignBean record);

    List<WeekSignBean> selectByExampleWithRowbounds(WeekSignBeanCriteria example, RowBounds rowBounds);

    List<WeekSignBean> selectByExample(WeekSignBeanCriteria example);

    int updateByExampleSelective(@Param("record") WeekSignBean record, @Param("example") WeekSignBeanCriteria example);

    int updateByExample(@Param("record") WeekSignBean record, @Param("example") WeekSignBeanCriteria example);
}