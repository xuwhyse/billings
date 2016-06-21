package com.hundsun.internet.billingsystem.common;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.hundsun.internet.billingsystem.bean.util.MenuBean;
import com.hundsun.internet.billingsystem.bean.util.MyUtil;
import com.hundsun.internet.billingsystem.bean.util.UserBean;



/**
 * .
 * @author: xunmin
 * @since: 2014-8-8 下午4:31:53
 * @history:
 */
public class BaseController {

    /**
     * @Fields MENU_LIST_PARA_NAME MODEL中菜单列表的名字
     */
    protected static final String MENU_LIST_PARA_NAME = "menuList";
    protected static final String PARA_OBJECT_PARA_NAME = "paraObj";// paraObj

    @ModelAttribute("user")
    public UserBean setUser(Model model) {
    	UserBean user = new UserBean();
    	List<MenuBean> listMenu = MyUtil.listMenu;
    	model.addAttribute("menuList", listMenu);
    	
        return user;
    }
    /**.
     * 设置查询参数，用于分页和返回。
     * @return 变量
     * @param request 对象
     * @param response 响应
     * @create: 2014年1月21日 上午9:19:19 shangke
     * @history:  2014年1月21日 上午9:19:19 shangke
     */
    @ModelAttribute(PARA_OBJECT_PARA_NAME)
    public ParasObject setParas(HttpServletRequest request,
            HttpServletResponse response) {
        ParasObject po = new ParasObject();

        // 自定义的可变参数
        HashSet<String> cusSet = new HashSet<String>();
        cusSet.add("page");
        cusSet.add("msg");

        AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(
            "/**/list.do");
        if (requestMatcher.matches(request)) {
            Enumeration names = request.getParameterNames();

            while (names.hasMoreElements()) {
                String name = names.nextElement().toString();

                if (name.toLowerCase().equals(
                    Constant.URL_TOKEN_PARA_NAME.toLowerCase())
                        || name.equals(Constant.POOL_ID_KEY_IN_MODEL)
                        || name.toLowerCase().equals("title")
                        || name.toLowerCase().equals("ctx")) {
                    continue;
                }

                if (cusSet.contains(name)) {
                    po.addParaCus(name, request.getParameter(name));
                } else {
                    po.addPara(name, request.getParameter(name));
                }
            }
            // java.net.URLEncoder.encode(s, enc)
            Cookie cookie = new Cookie("paras", po.getParas());
            Cookie cookieCus = new Cookie("paras_c", po.getParasCus());
            Cookie cookieUri = new Cookie("paras_uri", request.getRequestURI());
            response.addCookie(cookie);
            response.addCookie(cookieCus);
        } else {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equals("paras")) {
                        po.setParas(cookie.getValue());
                    } else if (cookie.getName().equals("paras_c")) {
                        po.setParasCus(cookie.getValue());
                    } else if (cookie.getName().equals("paras_uri")) {
                        po.setUri(cookie.getValue());
                    }
                }
            }
        }

        request.setAttribute(PARA_OBJECT_PARA_NAME, po);
        return po;
    }
    protected String sendError(BindingResult... results) {
        return sendErrors(results);
    }
    /**
     * formbean的验证
     * @author xunmin
     * @createTime 下午3:23:57
     * @param results
     */
    protected String sendErrors(BindingResult[] results) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < results.length; i++) {
            BindingResult result = results[i];
            if (result.hasErrors()) {
                List<FieldError> errors = result.getFieldErrors();
                for (int j = 0; j < errors.size(); j++) {
                    String mm = errors.get(j).getDefaultMessage(); // 出错的信息
                    str.append("(").append(mm).append(")");
                }
            }
        }
//        messageService.sendError2Page(str.toString());
        return str.toString();
    }

}
