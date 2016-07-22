package com.billings.billingsystem.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.billings.billingsystem.model.BaoyueBean;
import com.billings.billingsystem.model.BaoyueBeanCriteria;

public interface BaoyueBeanMapper {
    int countByExample(BaoyueBeanCriteria example);

    int deleteByExample(BaoyueBeanCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BaoyueBean record);

    int insertSelective(BaoyueBean record);

    List<BaoyueBean> selectByExampleWithRowbounds(BaoyueBeanCriteria example, RowBounds rowBounds);

    List<BaoyueBean> selectByExample(BaoyueBeanCriteria example);

    BaoyueBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaoyueBean record, @Param("example") BaoyueBeanCriteria example);

    int updateByExample(@Param("record") BaoyueBean record, @Param("example") BaoyueBeanCriteria example);

    int updateByPrimaryKeySelective(BaoyueBean record);

    int updateByPrimaryKey(BaoyueBean record);
}