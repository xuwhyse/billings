package com.hundsun.internet.billingsystem.service.billingsoper.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.hundsun.internet.billingsystem.model.SysAccountBean;
import com.hundsun.internet.billingsystem.model.SysAccountBeanCriteria;
import com.hundsun.internet.billingsystem.service.billingsoper.SysAccountMng;

@Service
public class SysAccountMngImpl implements SysAccountMng{

	/**
	 * 内存中能操作的账号
	 */
	public static Map<String, SysAccountBean> mapAccount = new HashMap<String, SysAccountBean>();
	
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
		String sql ="SELECT COUNT(*) FROM tb_sys_account WHERE sys_account_id LIKE '%" +
				searchname +"%'";
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<SysAccountBean> getInfoByPage(int pageIndex,String searchname) {
		SysAccountBeanCriteria example = new SysAccountBeanCriteria();
		if(searchname!=null && !searchname.equals(""))
			example.createCriteria().andSysAccountIdLike("%"+searchname+"%");
		example.setOrderByClause(" create_datetime DESC ");
		int pageSize = Constant.PER_PAGE_COUNT;
		RowBounds rowBounds = new RowBounds((pageIndex-1)*pageSize, pageSize);
		return sysAccountBeanMapper.selectByExampleWithRowbounds(example, rowBounds);
	}

	@Override
	public int saveSysAccount(SysAccountBean target,int type) {
		//只加流水，不更改账户金额
		Date time = new Date();
		target.setUpdateDatetime(time);
		if(type==1){
			String uuid = UUID.randomUUID().toString();
			target.setSysAccountId(uuid);
			target.setCreateDatetime(time);
			target.setCreator(createSys);
			return sysAccountBeanMapper.insert(target);
		}
		if(type==2){
			target.setUpdator(createSys);
			return sysAccountBeanMapper.updateByPrimaryKeySelective(target);
		}
		return -1;
	}

	@Override
	public SysAccountBean getSysAccountById(String id) {
		
		return sysAccountBeanMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByKey(String id) {
		return sysAccountBeanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSysAccounts() {
		List<SysAccountBean>  listBean = sysAccountBeanMapper.selectByExample(null);
		mapAccount.clear();
		for(int i=0;i<listBean.size();i++){
			String key = listBean.get(i).getAccountName();
			mapAccount.put(key, listBean.get(i));
		}
		return 0;
	}

}
