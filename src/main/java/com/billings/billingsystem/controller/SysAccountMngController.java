//package com.billings.billingsystem.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//@Controller
//public class SysAccountMngController extends BaseController {
//	
//	@Autowired
//	MessageService messageService;
//	
//	@Autowired
//	SysAccountMng sysAccountMng;
//	
//	String mngPath = "/manager/views/";
//	
//	//=================================
//    @RequestMapping("/manager/sysaccount/list.do")
//    public String list(
//            @RequestParam(required = false, defaultValue = "1") int page,
//            String searchname,
//            Model model) {
//        int total = sysAccountMng.getTotalNumber(searchname);
//        // 创建分页模型
//        Page<SysAccountBean> pg = new Page<SysAccountBean>(page,
//            total, Constant.PER_PAGE_COUNT);
//        // 获取申请表数据
//        List<SysAccountBean> list = sysAccountMng.getInfoByPage(pg.getPageIndex(),searchname);
//        pg.setResult(list);
//        model.addAttribute("pg", pg);
//        model.addAttribute("searchname", searchname);
//        return mngPath+"sysaccount/list";//super.go("viewPath" + "/list", model, false); 
//    }
//    @RequestMapping("/manager/sysaccount/add.do")
//    public String add(
//            @RequestParam(required = false, defaultValue = "1") int page,
//            String searchname,
//            Model model) {
//    	
//    	model.addAttribute("page", page);
//    	model.addAttribute("searchname", searchname);
//        return mngPath+"sysaccount/edit";//super.go("viewPath" + "/list", model, false); 
//    }
//    //----------------------------------------
//    @RequestMapping("/manager/sysaccount/modify.do")
//    public String modify(
//            @RequestParam(required = false, defaultValue = "1") int page,
//            String searchname,String id,
//            Model model) {
//    	
//    	SysAccountBean scoreBean =  sysAccountMng.getSysAccountById(id);
//    	model.addAttribute("page", page);
//    	model.addAttribute("searchname", searchname);
//    	model.addAttribute("myBean", scoreBean);
//        return mngPath+"sysaccount/edit";//super.go("viewPath" + "/list", model, false); 
//    }
//    @RequestMapping("/manager/sysaccount/delete.do")
//    public String delete(
//            @RequestParam(required = false, defaultValue = "1") int page,
//            String searchname,String id,
//            Model model) {
//    	
//    	sysAccountMng.deleteByKey(id);
//    	return "redirect:/manager/sysaccount/list.do?page="+page;
//    }
//  //----------------------------------------
//    @RequestMapping("/manager/sysaccount/save.do")
//    public String save(@Valid SysAccountForm  scoreForm,BindingResult result, Model model) {
//    	 // 表单验证
//        if (result.hasErrors()) {
//            messageService.sendError2Page(super.sendError(result));
//            model.addAttribute("myBean", scoreForm);
//            return mngPath+"sysaccount/edit";
//        }
//        SysAccountBean target = new SysAccountBean();
//        BeanUtilEx.copyProperties(target, scoreForm);
//        int type = 1;
//        String userId2 = scoreForm.getSysAccountId2();
//        if(userId2!=null && !userId2.equals("")){
//        	type = 2;
//        	target.setSysAccountId(userId2);
//        }
//        int flag = sysAccountMng.saveSysAccount(target,type);
//        String msg = "操作成功！";
//        if(flag<0)
//        	msg = "操作失败！";
//        sysAccountMng.updateSysAccounts();
//        messageService.sendInfo2Page(msg);
//        return "redirect:/manager/sysaccount/list.do?page="+scoreForm.getPage();//super.go("viewPath" + "/list", model, false); 
//    }
//    //=============================================================
//
//}
