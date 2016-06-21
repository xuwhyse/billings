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
import com.hundsun.internet.billingsystem.model.UserDatumBean;
import com.hundsun.internet.billingsystem.service.billingsoper.UserDatumMng;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;
import com.hundsun.internet.ihoms2.form.manager.score.ScoreForm;

@Controller
public class UserDatumMngController extends BaseController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserDatumMng userDatumMng;
	
	String mngPath = "/manager/views/";
	
	//=================================
    @RequestMapping("/manager/userdatum/list.do")
    public String list(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
        int total = userDatumMng.getTotalNumber(searchname);
        // 创建分页模型
        Page<UserDatumBean> pg = new Page<UserDatumBean>(page,
            total, Constant.PER_PAGE_COUNT);
        // 获取申请表数据
        List<UserDatumBean> list = userDatumMng.getInfoByPage(pg.getPageIndex(),searchname);
        pg.setResult(list);
        model.addAttribute("pg", pg);
        model.addAttribute("searchname", searchname);
        return mngPath+"userdatum/list";//super.go("viewPath" + "/list", model, false); 
    }
    public String add(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,
            Model model) {
    	
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
        return mngPath+"userscore/edit";//super.go("viewPath" + "/list", model, false); 
    }
    //----------------------------------------
    public String modify(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,String id,
            Model model) {
    	
//    	ScoreBean scoreBean =  scoreMng.getUserScoreById(id);
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
//    	model.addAttribute("myBean", scoreBean);
        return mngPath+"userscore/edit";//super.go("viewPath" + "/list", model, false); 
    }
    public String delete(
            @RequestParam(required = false, defaultValue = "1") int page,
            String searchname,@RequestParam(required = true)String id,
            Model model) {
    	
//    	scoreMng.deleteByKey(id);
    	model.addAttribute("page", page);
    	model.addAttribute("searchname", searchname);
    	return "redirect:/manager/score/list.do?page="+page;
    }
  //----------------------------------------
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
//        int flag = scoreMng.saveScore(target,type);
        String msg = "操作成功！";
//        if(flag<0)
//        	msg = "操作失败！";
        messageService.sendInfo2Page(msg);
        return "redirect:/manager/score/list.do?page="+scoreForm.getPage();//super.go("viewPath" + "/list", model, false); 
    }
    //=============================================================

}
