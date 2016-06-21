package com.hundsun.internet.billingsystem.dao;

import com.hundsun.internet.billingsystem.model.BaoyuePPBean;
import com.hundsun.internet.billingsystem.model.BaoyuePPBeanCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BaoyuePPBeanMapper {
    int countByExample(BaoyuePPBeanCriteria example);

    int deleteByExample(BaoyuePPBeanCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BaoyuePPBean record);

    int insertSelective(BaoyuePPBean record);

    List<BaoyuePPBean> selectByExampleWithBLOBsWithRowbounds(BaoyuePPBeanCriteria example, RowBounds rowBounds);

    List<BaoyuePPBean> selectByExampleWithBLOBs(BaoyuePPBeanCriteria example);

    List<BaoyuePPBean> selectByExampleWithRowbounds(BaoyuePPBeanCriteria example, RowBounds rowBounds);

    List<BaoyuePPBean> selectByExample(BaoyuePPBeanCriteria example);

    BaoyuePPBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BaoyuePPBean record, @Param("example") BaoyuePPBeanCriteria example);

    int updateByExampleWithBLOBs(@Param("record") BaoyuePPBean record, @Param("example") BaoyuePPBeanCriteria example);

    int updateByExample(@Param("record") BaoyuePPBean record, @Param("example") BaoyuePPBeanCriteria example);

    int updateByPrimaryKeySelective(BaoyuePPBean record);

    int updateByPrimaryKeyWithBLOBs(BaoyuePPBean record);

    int updateByPrimaryKey(BaoyuePPBean record);
}