package com.hundsun.internet.ihoms2.form.manager.score;

import java.io.Serializable;
import com.hundsun.internet.ihoms2.form.BaseForm;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.hundsun.internet.ihoms2.form.constraints.*;

/** 自动生成的Form.
 * @author: linyy
 * @since: 2014-11-07 10:56:22 星期五
 * @history: 历史第一次生成
 */
public class ScoreForm extends BaseForm implements Serializable {

    /**
     * @Fields serialVersionUID : you can do better!
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields userId2 : you can do better!
     */
    private String userId2;

    /**
     * @Fields page : you can do better!
     */
    private String page;

    /**
     * @Fields searchname : you can do better!
     */
    private String searchname;

    /**
     * @Fields userId : 用户ID
     */
    @Size(max = 50 , message = "内容不能超过50个字")
    @NotEmpty
    private String userId;

    /**
     * @Fields state : 状态
     */
    private String state;

    /**
     * @Fields scoreNum : 积分数
     */
    @Size(max = 10 , message = "内容不能超过10个字")
    @SelfInteger
    private String scoreNum;

    /**
     * @Fields goodLuckCoinNum : 红运币数
     */
    @Size(max = 10 , message = "内容不能超过10个字")
    @SelfInteger
    private String goodLuckCoinNum;

    /**这是一个高贵的get方法.
     * @return userId2
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getUserId2() {
        return userId2;
    }

    /**这是一个低调的getHTML方法.
     * @return userId2
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getUserId2HTML() {
        return userId2;
    }

    /**这是一个冷艳的set方法.
     * @param userId2 userId2
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    /**这是一个高贵的get方法.
     * @return page
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getPage() {
        return page;
    }

    /**这是一个低调的getHTML方法.
     * @return page
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getPageHTML() {
        return page;
    }

    /**这是一个冷艳的set方法.
     * @param page page
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**这是一个高贵的get方法.
     * @return searchname
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSearchname() {
        return searchname;
    }

    /**这是一个低调的getHTML方法.
     * @return searchname
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getSearchnameHTML() {
        return searchname;
    }

    /**这是一个冷艳的set方法.
     * @param searchname searchname
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    /**这是一个高贵的get方法.
     * @return userId
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getUserId() {
        return userId;
    }

    /**这是一个低调的getHTML方法.
     * @return userId
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getUserIdHTML() {
        return userId;
    }

    /**这是一个冷艳的set方法.
     * @param userId userId
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**这是一个高贵的get方法.
     * @return state
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getState() {
        return state;
    }

    /**这是一个低调的getHTML方法.
     * @return state
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getStateHTML() {
        return state;
    }

    /**这是一个冷艳的set方法.
     * @param state state
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setState(String state) {
        this.state = state;
    }

    /**这是一个高贵的get方法.
     * @return scoreNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getScoreNum() {
        return scoreNum;
    }

    /**这是一个低调的getHTML方法.
     * @return scoreNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getScoreNumHTML() {
        return scoreNum;
    }

    /**这是一个冷艳的set方法.
     * @param scoreNum scoreNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    /**这是一个高贵的get方法.
     * @return goodLuckCoinNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getGoodLuckCoinNum() {
        return goodLuckCoinNum;
    }

    /**这是一个低调的getHTML方法.
     * @return goodLuckCoinNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public String getGoodLuckCoinNumHTML() {
        return goodLuckCoinNum;
    }

    /**这是一个冷艳的set方法.
     * @param goodLuckCoinNum goodLuckCoinNum
     * @create: 2014-11-07 10:56:22 星期五 linyy
     * @history: 历史第一次生成
     */
    public void setGoodLuckCoinNum(String goodLuckCoinNum) {
        this.goodLuckCoinNum = goodLuckCoinNum;
    }

}