package com.billings.billingsystem.bean.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.billings.billingsystem.common.Util;
import com.billings.billingsystem.model.TradeFlowBean;


public class MyUtil {

	public static List<MenuBean> listMenu;
	static{
		listMenu = new ArrayList<MenuBean>();
		MenuBean item = new MenuBean();
		item = new MenuBean();
		item.setMenuid("88");
		item.setMenuname("第一阶段功能");
		item.setUrl("#");
		listMenu.add(item);
		
		item = new MenuBean();
		item.setMenuid("1");
		item.setMenuname("交易流水");
		item.setParentmenuid("88");
		item.setUrl("/manager/billings/list.do");
		listMenu.add(item);
		
		item = new MenuBean();
		item.setMenuid("2");
		item.setMenuname("用户积分");
		item.setParentmenuid("88");
		item.setUrl("/manager/score/list.do");
		listMenu.add(item);
		
		item = new MenuBean();
		item.setMenuid("3");
		item.setMenuname("基准");
		item.setParentmenuid("88");
		item.setUrl("/manager/datum/list.do");
		listMenu.add(item);
		
		item = new MenuBean();
		item.setMenuid("4");
		item.setMenuname("用户基准");
		item.setParentmenuid("88");
		item.setUrl("/manager/userdatum/list.do");
		listMenu.add(item);
		
		item = new MenuBean();
		item.setMenuid("5");
		item.setMenuname("系统账户");
		item.setParentmenuid("88");
		item.setUrl("/manager/sysaccount/list.do");
		listMenu.add(item);
	}
	/**
	 * 将bean转化成自定义字符窜
	 * @author xunmin
	 * @createTime 下午6:53:05
	 * @param bean
	 * @param flag 
	 * @return
	 */
	public static String getMyTypeStr(TradeFlowBean bean, int flag) {
		//flowId=2323;userId=sfd;sysAccountId=dsg;creator=
		String tempStr;
		StringBuilder str = new StringBuilder();
		String temp = ";";
		str.append("flowId=");
		str.append(bean.getFlowId());
		str.append(temp);
		//userId
		str.append("userId=");
		str.append(bean.getUserId());
		str.append(temp);
		
		str.append("sysAccountId=");
		tempStr = bean.getSysAccountId();
		str.append(tempStr);
		str.append(temp);
		
		str.append("creator=");
		tempStr = bean.getCreator();
		str.append(tempStr);
		str.append(temp);
		
		str.append("createDatetime=");
		tempStr = null;
		Date  time = bean.getCreateDatetime();
		if(time!=null)
			tempStr = Util.date2StringWithHHmmss(bean.getCreateDatetime());
		str.append(tempStr);
		str.append(temp);
		
		str.append("scoreNum=");
		str.append(bean.getScoreNum());
		str.append(temp);
		
		str.append("goodLuckCoinNum=");
		str.append(bean.getGoodLuckCoinNum());
		str.append(temp);
		
		str.append("orderNum=");
		tempStr = bean.getOrderNum();
		str.append(tempStr);
		str.append(temp);
		
		str.append("tradeType=");
		str.append(bean.getTradeType());
		str.append(temp);
		
		str.append("flag=");
		str.append(flag);
		
		return str.toString();
	}
}
