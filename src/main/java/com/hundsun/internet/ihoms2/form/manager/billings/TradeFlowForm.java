package com.hundsun.internet.ihoms2.form.manager.billings;

import java.io.Serializable;
import com.hundsun.internet.ihoms2.form.BaseForm;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import com.hundsun.internet.ihoms2.form.constraints.*;

/** 自动生成的Form.
 * @author: linyy
 * @since: 2014-11-06 15:17:03 星期四
 * @history: 历史第一次生成
 */
public class TradeFlowForm extends BaseForm implements Serializable {

    /**
     * @Fields serialVersionUID : you can do better!
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * @Fields orderNum : 订单号
     */
    @Size(max = 100 , message = "内容不能超过100个字")
    private String orderNum;

    /**
     * @Fields tradeType : 交易类型
     */
    private String tradeType;

    /**这是一个高贵的get方法.
     * @return page
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getPage() {
        return page;
    }

    /**这是一个低调的getHTML方法.
     * @return page
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getPageHTML() {
        return page;
    }

    /**这是一个冷艳的set方法.
     * @param page page
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**这是一个高贵的get方法.
     * @return searchname
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getSearchname() {
        return searchname;
    }

    /**这是一个低调的getHTML方法.
     * @return searchname
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getSearchnameHTML() {
        return searchname;
    }

    /**这是一个冷艳的set方法.
     * @param searchname searchname
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    /**这是一个高贵的get方法.
     * @return userId
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getUserId() {
        return userId;
    }

    /**这是一个低调的getHTML方法.
     * @return userId
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getUserIdHTML() {
        return userId;
    }

    /**这是一个冷艳的set方法.
     * @param userId userId
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**这是一个高贵的get方法.
     * @return scoreNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getScoreNum() {
        return scoreNum;
    }

    /**这是一个低调的getHTML方法.
     * @return scoreNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getScoreNumHTML() {
        return scoreNum;
    }

    /**这是一个冷艳的set方法.
     * @param scoreNum scoreNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    /**这是一个高贵的get方法.
     * @return goodLuckCoinNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getGoodLuckCoinNum() {
        return goodLuckCoinNum;
    }

    /**这是一个低调的getHTML方法.
     * @return goodLuckCoinNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getGoodLuckCoinNumHTML() {
        return goodLuckCoinNum;
    }

    /**这是一个冷艳的set方法.
     * @param goodLuckCoinNum goodLuckCoinNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setGoodLuckCoinNum(String goodLuckCoinNum) {
        this.goodLuckCoinNum = goodLuckCoinNum;
    }

    /**这是一个高贵的get方法.
     * @return orderNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**这是一个低调的getHTML方法.
     * @return orderNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getOrderNumHTML() {
        return orderNum;
    }

    /**这是一个冷艳的set方法.
     * @param orderNum orderNum
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**这是一个高贵的get方法.
     * @return tradeType
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getTradeType() {
        return tradeType;
    }

    /**这是一个低调的getHTML方法.
     * @return tradeType
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public String getTradeTypeHTML() {
        return tradeType;
    }

    /**这是一个冷艳的set方法.
     * @param tradeType tradeType
     * @create: 2014-11-06 15:17:03 星期四 linyy
     * @history: 历史第一次生成
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

}