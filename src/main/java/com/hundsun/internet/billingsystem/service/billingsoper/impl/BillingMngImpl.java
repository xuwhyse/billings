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
import com.hundsun.internet.billingsystem.model.TradeFlowBean;
import com.hundsun.internet.billingsystem.model.TradeFlowBeanCriteria;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingMng;

@Service
public class BillingMngImpl implements BillingMng{

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
		String sql ="SELECT COUNT(*) FROM tb_trade_flow WHERE user_id LIKE '%" +
				searchname +"%'";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<TradeFlowBean> getInfoByPage(int pageIndex, String searchname) {
		TradeFlowBeanCriteria example = new TradeFlowBeanCriteria();
		if(searchname!=null && !searchname.equals(""))
			example.createCriteria().andUserIdLike("%"+searchname+"%");
		example.setOrderByClause(" flow_id DESC ");
		int pageSize = Constant.PER_PAGE_COUNT;
		RowBounds rowBounds = new RowBounds((pageIndex-1)*pageSize, pageSize);
		return tradeFlowBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public int saveTradeFlow(TradeFlowBean target) {
		// TODO Auto-generated method stub
		int state = BillingsOperImpl.existAccount(target.getUserId(),jdbcTemplate,null);
		//1.检测账号是否存在
		if(state!=0)
			return state;
		//只加流水，不更改账户金额
		target.setCreateDatetime(new Date());
		target.setCreator("system");
		target.setOrderNum("systemOder");
		tradeFlowBeanMapper.insert(target);
		if(target.getTradeType()==1) //正常交易，就要改变账户的金额
			BillingsOperImpl.updateScoreByselect(target, jdbcTemplate);
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getTotalNumber2(String id) {
		if(id==null)
			id="";
		String sql ="SELECT COUNT(*) FROM tb_trade_flow WHERE user_id = '" +
				id +"'";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<TradeFlowBean> getInfoByPage2(int pageIndex, String id) {
		TradeFlowBeanCriteria example = new TradeFlowBeanCriteria();
		example.createCriteria().andUserIdEqualTo(id);
		example.setOrderByClause(" flow_id DESC ");
		int pageSize = Constant.PER_PAGE_COUNT;
		RowBounds rowBounds = new RowBounds((pageIndex-1)*pageSize, pageSize);
		return tradeFlowBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}


}
