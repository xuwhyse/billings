package com.hundsun.internet.billingsystem.service.billingsoper;

import java.util.Date;

import com.hundsun.internet.billingsystem.bean.util.MyScoreBean;
import com.hundsun.internet.billingsystem.bean.util.TransferRes;
import com.hundsun.internet.billingsystem.model.TradeFlowBean;

public interface BillingsOper {

	/**
	 * 给某个账户增加x资金(流水和总数都会记录变更)
	 * @author xunmin
	 * @createTime 下午4:22:54
	 * @param bean
	 * @return 0成功；1正在等待前一次处理 ； 2欲操作账号不存在 ；3账号冻结 ；4账号删除 ；-1后台合算账单
	 */
	int raise(TradeFlowBean bean);
	/**
	 * 给每个账户扣除x资金
	 * @author xunmin
	 * @createTime 下午4:23:54
	 * @param bean
	 * @return 0成功；1正在等待前一次处理； 2欲操作账号不存在 ；-1:系统繁忙 ；3账号冻结 4账号删除；-1后台合算账单
	 */
	int reduce(TradeFlowBean bean);
	/**
	 * 根据交易流水和上次成功的基准来合算今天的账目
	 * @author xunmin
	 * @createTime 下午3:23:11
	 * @return 0:成功；1：正在合算 
	 */
	int balance();
	/**
	 * 查询时间段内，该账号的积分及其流水账单
	 * @author xunmin
	 * @createTime 下午12:36:47
	 * @param userId
	 * @param date1
	 * @param date2
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	MyScoreBean queryScores(String userId, Date d1, Date d2, int offset,
			int pageSize);
	/**
	 * 转账或者批处理账户时，一个事务操作
	 * @author xunmin
	 * @createTime 下午3:06:21
	 * @param bean
	 * @return
	 */
	TransferRes transfer(TransferRes bean);
	/**
	 * 传过来的是用户名，反查成用户id
	 * @author xunmin
	 * @createTime 下午7:14:51
	 * @param userId
	 * @return
	 */
	String getRealIdByName(String userId);
}
