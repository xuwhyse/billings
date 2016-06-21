package com.hundsun.internet.ihoms2.form.manager.sysaccount;

import java.io.Serializable;
import com.hundsun.internet.ihoms2.form.BaseForm;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/** 自动生成的Form.
 * @author: linyy
 * @since: 2014-11-07 17:21:51 星期五
 * @history: 历史第一次生成
 */
public class SysAccountForm extends BaseForm implements Serializable {

    /**
     * @Fields serialVersionUID : you can do better!
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields sysAccountId2 : you can do better!
     */
    private String sysAccountId2;

    /**
     * @Fields page : you can do better!
     */
    private String page;

    /**
     * @Fields searchname : you can do better!
     */
    private String searchname;

    /**
     * @Fields accountName : 账户名
     */
    @Size(max = 50 , message = "内容不能超过50个字")
    @NotEmpty
    private String accountName;

    /**
     * @Fields accountPwd : 密码
     */
    @Size(max = 50 , message = "内容不能超过50个字")
    @NotEmpty
    private String accountPwd;

    /**这是一个高贵的get方法.
     * @return sysAccountId2
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSysAccountId2() {
        return sysAccountId2;
    }

    /**这是一个低调的getHTML方法.
     * @return sysAccountId2
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSysAccountId2HTML() {
        return sysAccountId2;
    }

    /**这是一个冷艳的set方法.
     * @param sysAccountId2 sysAccountId2
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setSysAccountId2(String sysAccountId2) {
        this.sysAccountId2 = sysAccountId2;
    }

    /**这是一个高贵的get方法.
     * @return page
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getPage() {
        return page;
    }

    /**这是一个低调的getHTML方法.
     * @return page
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getPageHTML() {
        return page;
    }

    /**这是一个冷艳的set方法.
     * @param page page
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**这是一个高贵的get方法.
     * @return searchname
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSearchname() {
        return searchname;
    }

    /**这是一个低调的getHTML方法.
     * @return searchname
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSearchnameHTML() {
        return searchname;
    }

    /**这是一个冷艳的set方法.
     * @param searchname searchname
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    /**这是一个高贵的get方法.
     * @return accountName
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getAccountName() {
        return accountName;
    }

    /**这是一个低调的getHTML方法.
     * @return accountName
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getAccountNameHTML() {
        return accountName;
    }

    /**这是一个冷艳的set方法.
     * @param accountName accountName
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**这是一个高贵的get方法.
     * @return accountPwd
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getAccountPwd() {
        return accountPwd;
    }

    /**这是一个低调的getHTML方法.
     * @return accountPwd
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getAccountPwdHTML() {
        return accountPwd;
    }

    /**这是一个冷艳的set方法.
     * @param accountPwd accountPwd
     * @create: 2014-11-07 17:21:51 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

}