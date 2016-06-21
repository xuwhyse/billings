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
import com.hundsun.internet.billingsystem.model.ScoreBean;
import com.hundsun.internet.billingsystem.model.TradeFlowBean;
import com.hundsun.internet.billingsystem.service.billingsoper.BillingMng;
import com.hundsun.internet.billingsystem.service.billingsoper.ScoreMng;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;
import com.hundsun.internet.ihoms2.form.manager.score.ScoreForm;

@Controller
public class ScoreMngController extends BaseController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	BillingMng billingMng;
	
	@Autowired
	ScoreMng scoreMng;
	
	String mngPath = "/manager/views/";
	
	//=================================
    @RequestMapping("/manager/score/list.do")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
        int total = scoreMng.getTotalNumber(searchname);
        // 创建分页模型
        Page<ScoreBean> pg = new Page<ScoreBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<ScoreBean> list = scoreMng.getInfoByPage(pg.getPageIndex(),searchname);
        pg.setResult(list);
        model.addAttribute("pg", pg);
        model.addAttribute("searchname", searchname);
        return mngPath+"userscore/list";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/score/query.do")
    public String query(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,String id,
            Model model) {
        int total = billingMng.getTotalNumber2(id);
        // 创建分页模型
        Page<TradeFlowBean> pg = new Page<TradeFlowBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<TradeFlowBean> list = billingMng.getInfoByPage2(pg.getPageIndex(),id);
        pg.setResult(list);
        model.addAttribute("pg", pg);
        model.addAttribute("userId", id);
//        model.addAttribute("searchname", searchname);
        return mngPath+"userscore/list2";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/score/add.do")
    public String add(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
    	
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
        return mngPath+"userscore/edit";//super.go("viewPath" + "/list", model, false); 
    }
    //----------------------------------------
    @RequestMapping("/manager/score/modify.do")
    public String modify(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,String id,
            Model model) {
    	
    	ScoreBean scoreBean =  scoreMng.getUserScoreById(id);
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
    	model.addAttribute("myBean", scoreBean);
        return mngPath+"userscore/edit";//super.go("viewPath" + "/list", model, false); 
    }
    @RequestMapping("/manager/score/delete.do")
    public String delete(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,@RequestParam(required = true)String id,
            Model model) {
    	
    	scoreMng.deleteByKey(id);
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
    	return "redirect:/manager/score/list.do?page="+page;
    }
  //----------------------------------------
    @RequestMapping("/manager/score/save.do")
    public String save(@Valid ScoreForm  scoreForm,BindingResult result, Model model) {
    	 // 表单验证
        if (result.hasErrors()) {
            messageService.sendError2Page(super.sendError(result));
            model.addAttribute("myBean", scoreForm);
            return mngPath+"userscore/edit";
        }
        ScoreBean target = new ScoreBean();
        BeanUtilEx.copyProperties(target, scoreForm);
        int type = 1;
        String userId2 = scoreForm.getUserId2();
        if(userId2!=null && !userId2.equals(""))
        	type = 2;
        int flag = scoreMng.saveScore(target,type);
        String msg = "操作成功！";
        if(flag<0)
        	msg = "操作失败！";
        messageService.sendInfo2Page(msg);
        return "redirect:/manager/score/list.do?page="+scoreForm.getPage();//super.go("viewPath" + "/list", model, false); 
    }
    //=============================================================

}
