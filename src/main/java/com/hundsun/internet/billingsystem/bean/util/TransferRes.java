package com.hundsun.internet.billingsystem.bean.util;

import java.util.List;

import com.hundsun.internet.billingsystem.model.TradeFlowBean;

/**
 * 批量转账等操作返回回来的结构
 * @author xunmin
 * @createTime 下午2:58:38
 */
public class TransferRes {

	private  int flag;
	private String msg="操作成功";
	private List<TradeFlowBean> listTradeFlowBean;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<TradeFlowBean> getListTradeFlowBean() {
		return listTradeFlowBean;
	}
	public void setListTradeFlowBean(List<TradeFlowBean> listTradeFlowBean) {
		this.listTradeFlowBean = listTradeFlowBean;
	}
}
