package com.hundsun.internet.billingsystem.service.billingsoper.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hundsun.internet.billingsystem.common.Constant;
import com.hundsun.internet.billingsystem.dao.DatumBeanMapper;
import com.hundsun.internet.billingsystem.dao.ScoreBeanMapper;
import com.hundsun.internet.billingsystem.dao.SysAccountBeanMapper;
import com.hundsun.internet.billingsystem.dao.TradeFlowBeanMapper;
import com.hundsun.internet.billingsystem.dao.UserDatumBeanMapper;
import com.hundsun.internet.billingsystem.model.ScoreBean;
import com.hundsun.internet.billingsystem.model.UserDatumBean;
import com.hundsun.internet.billingsystem.model.UserDatumBeanCriteria;
import com.hundsun.internet.billingsystem.service.billingsoper.UserDatumMng;

@Service
public class UserDatumMngImpl implements UserDatumMng{

	String createSys;
	@Autowired
	JdbcTemplate  jdbcTemplate;
	/**
	 * 交易流水表操作
	 */
	@Autowired
	TradeFlowBeanMapper  tradeFlowBeanMapper;
	/**
	 * 用户积分表操作
	 */
	@Autowired
	ScoreBeanMapper  scoreBeanMapper;
	/**
	 * 第三方调用用户表
	 */
	@Autowired
	SysAccountBeanMapper  sysAccountBeanMapper;
	/**
	 * 用户基准表
	 */
	@Autowired
	UserDatumBeanMapper  userDatumBeanMapper;
	/**
	 * 基准表
	 */
	@Autowired
	DatumBeanMapper  datumBeanMapper;
	//=====================================================
	@SuppressWarnings("deprecation")
	@Override
	public int getTotalNumber(String searchname) {
		if(searchname==null)
			searchname="";
		String sql ="SELECT COUNT(*) FROM tb_user_datum WHERE datum_id LIKE '%" +
				searchname +"%'";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<UserDatumBean> getInfoByPage(int pageIndex,String searchname) {
		UserDatumBeanCriteria example = new UserDatumBeanCriteria();
		if(searchname!=null && !searchname.equals(""))
			example.createCriteria().andDatumIdLike("%"+searchname+"%");
//		example.setOrderByClause(orderByClause)
		int pageSize = Constant.PER_PAGE_COUNT;
		RowBounds rowBounds = new RowBounds((pageIndex-1)*pageSize, pageSize);
		return userDatumBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public int saveScore(ScoreBean target,int type) {
		//只加流水，不更改账户金额
		Date time = new Date();
		target.setUpdateDatetime(time);
		if(type==1){
			target.setCreateDatetime(time);
			target.setCreator(createSys);
			return scoreBeanMapper.insert(target);
		}
		if(type==2){
			target.setUpdator(createSys);
			return scoreBeanMapper.updateByPrimaryKeySelective(target);
		}
		return -1;
	}

	@Override
	public ScoreBean getUserScoreById(String id) {
		
		return scoreBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByKey(String id) {
		return scoreBeanMapper.deleteByPrimaryKey(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getTotalNumber2(String id) {
		String sql ="SELECT COUNT(*) FROM tb_user_datum WHERE datum_id = '" +
				id +"'";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<UserDatumBean> getInfoByPage2(int pageIndex, String id) {
		UserDatumBeanCriteria example = new UserDatumBeanCriteria();
		example.createCriteria().andDatumIdEqualTo(id);
//		example.setOrderByClause(orderByClause)
		int pageSize = Constant.PER_PAGE_COUNT;
		RowBounds rowBounds = new RowBounds((pageIndex-1)*pageSize, pageSize);
		return userDatumBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

}
