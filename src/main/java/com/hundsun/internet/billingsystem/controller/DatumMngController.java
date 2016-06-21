package com.hundsun.internet.billingsystem.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.internet.billingsystem.common.BaseController;
import com.hundsun.internet.billingsystem.common.Constant;
import com.hundsun.internet.billingsystem.common.Page;
import com.hundsun.internet.billingsystem.model.DatumBean;
import com.hundsun.internet.billingsystem.model.UserDatumBean;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingsOper;
import com.hundsun.internet.billingsystem.service.billingsoper.DatumMng;
import com.hundsun.internet.billingsystem.service.billingsoper.UserDatumMng;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;

@Controller
public class DatumMngController extends BaseController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserDatumMng userDatumMng;
	
	@Autowired
	DatumMng datumMng;
	
	@Autowired
	BillingsOper billingsOper;
	
	String mngPath = "/manager/views/";
	
	//=================================
    @RequestMapping("/manager/datum/list.do")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
        int total = datumMng.getTotalNumber(searchname);
        // 创建分页模型
        Page<DatumBean> pg = new Page<DatumBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<DatumBean> list = datumMng.getInfoByPage(pg.getPageIndex(),searchname);
        pg.setResult(list);
        model.addAttribute("pg", pg);
        model.addAttribute("searchname", searchname);
        return mngPath+"datum/list";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/datum/query.do")
    public String query(
            @RequestParam(required = false, defaultValue = "1") int page,
            String id,
            Model model) {
        int total = userDatumMng.getTotalNumber2(id);
        // 创建分页模型
        Page<UserDatumBean> pg = new Page<UserDatumBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<UserDatumBean> list = userDatumMng.getInfoByPage2(pg.getPageIndex(),id);
        pg.setResult(list);
        model.addAttribute("pg", pg);
//        model.addAttribute("searchname", searchname);
        return mngPath+"datum/list2";//super.go("viewPath" + "/list", model, false); 
    }
    //=============================================================
    /**
     * 
     * @author xunmin
     * @createTime 上午10:40:07
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/manager/billings/balance.do")
    public String balance(Model model) {
    	billingsOper.balance();
		return "redirect:/manager/datum/list.do";
    }
}
