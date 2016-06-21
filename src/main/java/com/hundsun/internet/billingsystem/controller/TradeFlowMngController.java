package com.hundsun.internet.billingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hundsun.internet.billingsystem.common.BaseController;
import com.hundsun.internet.billingsystem.common.BeanUtilEx;
import com.hundsun.internet.billingsystem.common.Constant;
import com.hundsun.internet.billingsystem.common.Page;
import com.hundsun.internet.billingsystem.model.TradeFlowBean;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingMng;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;
import com.hundsun.internet.ihoms2.form.manager.billings.TradeFlowForm;

@Controller
public class TradeFlowMngController extends BaseController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	BillingMng billingMng;
	
	String mngPath = "/manager/views/";
	
	//=================================
    @RequestMapping("/manager/billings/list.do")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
        int total = billingMng.getTotalNumber(searchname);
        // 创建分页模型
        Page<TradeFlowBean> pg = new Page<TradeFlowBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<TradeFlowBean> list = billingMng.getInfoByPage(pg.getPageIndex(),searchname);
        pg.setResult(list);
        model.addAttribute("pg", pg);
        model.addAttribute("searchname", searchname);
        return mngPath+"tradeflow/list";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/billings/add.do")
    public String add(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
    	
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
        return mngPath+"tradeflow/edit";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/billings/save.do")
    public String save(@Valid TradeFlowForm  tradeFlowForm,BindingResult result, Model model) {
    	 // 表单验证
        if (result.hasErrors()) {
            messageService.sendError2Page(super.sendError(result));
            model.addAttribute("tradeFlowForm", tradeFlowForm);
            return mngPath+"tradeflow/edit";
        }
        TradeFlowBean target = new TradeFlowBean();
        BeanUtilEx.copyProperties(target, tradeFlowForm);
        int flag = billingMng.saveTradeFlow(target);
        String msg = "操作成功！";
        if(flag==2)
        	msg = "账号不存在！";
        if(flag==3)
        	msg = "账号已冻结！";
        if(flag==4)
        	msg = "账号已删除！";
        messageService.sendInfo2Page(msg);
        return "redirect:/manager/billings/list.do?page="+tradeFlowForm.getPage();//super.go("viewPath" + "/list", model, false); 
    }
    //=============================================================

}
