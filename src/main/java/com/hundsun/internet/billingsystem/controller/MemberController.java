package com.hundsun.internet.billingsystem.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hundsun.internet.billingsystem.bean.util.GlobalObjects;
import com.hundsun.internet.billingsystem.bean.util.MyScoreBean;
import com.hundsun.internet.billingsystem.bean.util.MyUtil;
import com.hundsun.internet.billingsystem.bean.util.TransferRes;
import com.hundsun.internet.billingsystem.common.BaseController;
import com.hundsun.internet.billingsystem.common.JsonUtil;
import com.hundsun.internet.billingsystem.model.TradeFlowBean;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingMng;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingsOper;

/**
 * 
 * @author xunmin
 * @createTime 下午12:10:52
 */
@Controller
public class MemberController extends BaseController {
	
	@Autowired
	BillingsOper billingsOper;
	
	@Autowired
	BillingMng billingMng;
	
	String mngPath = "/resoult";
	
    //=============================================================
    @RequestMapping("/member/billings/raise/{userId}.do")
    @ResponseBody
    public String raise(@PathVariable("userId") String userId,
    		String goodLuckCoinNum,String scoreNum,String tradeType,String orderNum,
    		String sysAccountId,String password,String byName,
    		Model model) {
    	if(byName!=null && !byName.equals(""))
    		userId = billingsOper.getRealIdByName(userId);
    	if(userId==null)
    		return null;
    	TradeFlowBean bean = getTradeFlowBean(userId,goodLuckCoinNum,scoreNum,tradeType,orderNum);
    	int flag = billingsOper.raise(bean);
    	String str = MyUtil.getMyTypeStr(bean,flag);
		return str;
    }
    private TradeFlowBean getTradeFlowBean(String userId,
			String goodLuckCoinNum, String scoreNum, String tradeType,
			String orderNum) {
    	TradeFlowBean bean = new TradeFlowBean();
    	if(userId==null || userId.equals(""))
    		userId="-14";
    	bean.setUserId(userId);//充值账户
    	bean.setSysAccountId(GlobalObjects.getCurrentSysAccount().getSysAccountId());//操作管理员//这个是要查出来的
    	if(goodLuckCoinNum==null || goodLuckCoinNum.equals(""))
    		goodLuckCoinNum = "0";
    	bean.setGoodLuckCoinNum(Integer.parseInt(goodLuckCoinNum));
    	if(scoreNum==null || scoreNum.equals(""))
    		scoreNum = "0";
    	bean.setScoreNum(Integer.parseInt(scoreNum));
    	bean.setTradeType(1);//1 正常交易  2.人工冲红修正(走这边的是写死正常交易)
    	bean.setOrderNum(orderNum);
		return bean;
	}
	@RequestMapping("/member/billings/reduce/{userId}.do")
    @ResponseBody
    public String reduce(@PathVariable("userId") String userId,
    		String goodLuckCoinNum,String scoreNum,String tradeType,String orderNum,
    		String sysAccountId,String password,String byName,
    		Model model) {
		if(byName!=null && !byName.equals(""))
    		userId = billingsOper.getRealIdByName(userId);
		if(userId==null)
    		return null;
		TradeFlowBean bean = getTradeFlowBean(userId,goodLuckCoinNum,scoreNum,tradeType,orderNum);
    	int flag = billingsOper.reduce(bean);
    	String str = MyUtil.getMyTypeStr(bean,flag);
    	return str;
    }
	//默认 "text/plain;charset=ISO-8859-1" using [org.springframework.http.converter.StringHttpMessageConverter@4529796b]
	@SuppressWarnings("unchecked")
	@RequestMapping("/member/billings/transfer.do")
    @ResponseBody
    public String transfer(String jsonObject,String sysAccountId,String password,String byName,Model model) {
		//---------------
		List<LinkedHashMap<String, Object>>  listBeans = new ArrayList<LinkedHashMap<String,Object>>();
		listBeans = (List<LinkedHashMap<String, Object>>) JsonUtil.JsonToOb(jsonObject,listBeans.getClass());
		if(listBeans==null || listBeans.size()==0)
			return null;
		String sysAccount = GlobalObjects.getCurrentSysAccount().getSysAccountId();
		TransferRes bean = new TransferRes();
		List<TradeFlowBean> listFlows = new ArrayList<TradeFlowBean>();
		bean.setListTradeFlowBean(listFlows);
		for(int i=0;i<listBeans.size();i++){
			TradeFlowBean item = new TradeFlowBean();
			LinkedHashMap<String, Object> temp = listBeans.get(i);
			String userId = (String) temp.get("account");
			if(byName!=null && !byName.equals(""))
	    		userId = billingsOper.getRealIdByName(userId);
			if(userId==null)
	    		return null;
			item.setUserId(userId);
			item.setGoodLuckCoinNum((Integer) temp.get("goodLuckCoinNum"));
			item.setScoreNum((Integer) temp.get("scoreNum"));
			item.setOrderNum((String) temp.get("orderNum"));
			item.setSysAccountId(sysAccount);//操作管理员//这个是要查出来的
			
			listFlows.add(item);
		}
		try{
			bean = billingsOper.transfer(bean);
		}catch(RuntimeException e){
			String msg = e.getMessage();
			bean.setFlag(Integer.parseInt(msg));
		}
    	String str = JsonUtil.ObToJson(bean);
    	return str;
    }
    //===================================
    /**
     * 查询某个账户的信息以及流水
     * @author xunmin
     * @createTime 下午12:18:13
     * @param userId
     * @param date1
     * @param date2
     * @param offset
     * @param pageSize
     * @param sysAccountId
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/member/billings/query/{userId}.do")
    @ResponseBody
    public String query(@PathVariable("userId") String userId,
    		Long date1,Long date2,int offset,int pageSize,
    		String sysAccountId,String password,String byName,
    		Model model) {
    	Date d1 = null,d2 = null;
    	if(date1!=null)
    		d1 = new Date(date1);
    	if(date2!=null)
    		d2 =  new Date(date2);
    	if(byName!=null && !byName.equals(""))
    		userId = billingsOper.getRealIdByName(userId);
    	if(userId==null)
    		return null;
    	MyScoreBean myBean = billingsOper.queryScores(userId,d1,d2,offset,pageSize);
    	if(myBean==null)
    		return null;
    	String str = JsonUtil.ObToJson(myBean);
    	return str;
    }

}
