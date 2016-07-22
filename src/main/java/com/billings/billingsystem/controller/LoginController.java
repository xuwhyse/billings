package com.billings.billingsystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billings.billingsystem.common.BaseController;
import com.billings.billingsystem.security.AdminService;


/**
 * 
 * @author xunmin
 * @createTime 下午12:10:52
 */
@Controller
public class LoginController extends BaseController {
	
	String mngPath = "/manager/views/";
	
    @RequestMapping("/login.do")
    public String login(Model model) {
		return mngPath+"login";
    }
    @RequestMapping("logout.do")
    public String logout(HttpServletRequest request,HttpServletResponse response,Model model) {
    	AdminService.logout(response);
		return mngPath+"login";
    }
    @RequestMapping("/logindo.do")
    public String logindo(String username,String password,Model model,
    		HttpServletRequest request,HttpServletResponse response) {
    	int flag = AdminService.login(username,password,request,response);
    	if(flag==0){
    		return "redirect:/manager/score/list.do";
    	}
		return mngPath+"login";
    }
}
