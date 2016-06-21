package com.hundsun.internet.billingsystem.service.billingsoper;

import java.util.List;

import com.hundsun.internet.billingsystem.model.TradeFlowBean;

/**
 * 专门用于后台操作的
 * @author xunmin
 * @createTime 上午10:46:33
 */
public interface BillingMng {

	int getTotalNumber(String searchname);

	List<TradeFlowBean> getInfoByPage(int pageIndex, String searchname);

	/**
	 * 2欲操作账号不存在 ；3账号冻结 ；4账号删除 
	 * @author xunmin
	 * @createTime 下午3:44:54
	 * @param target
	 * @return 1正确
	 */
	int saveTradeFlow(TradeFlowBean target);

	int getTotalNumber2(String id);

	List<TradeFlowBean> getInfoByPage2(int pageIndex,
			String id);
 
}
