/**
 * @Title Constant.java
 * @Package com.hundsun.internet.p2p.controller.common
 * @Description
 * @author Administrator
 * @date 2014年1月9日 上午10:23:06
 * @version V1.0
 */
package com.hundsun.internet.billingsystem.common;

/** .
 * @author: shangke
 * @since: 2014年1月9日 上午10:23:06
 * @history:
 */
public final class Constant {

    /**
     * 私有构造函数.
     */
    private Constant() {
    };

    /**
     * @Fields PER_PAGE_COUNT : (每页显示数据的个数)
     */
    public static final int PER_PAGE_COUNT = 100;

    /**
     * @Fields INITIAL_PAGE : (页码初始值)
     */
    public static final int INITIAL_PAGE = 1;

    /**
     * @Fields ISCPIS : 运维人员
     */
    public static final int ISCPIS = 1;

    /**
     * @Fields COOKIES_CAPTACHA_ID_KEY 验证码组件缓存在COOKIES中的KEY值
     */
    public static final String COOKIES_CAPTACHA_ID_KEY = "captchaId";

    /**
     * @Fields COOKIE_ONE_WEEK : (COOKIE的有效时间：一周)
     */
    public static final int COOKIE_ONE_WEEK = 60 * 60 * 24 * 7;

    /**
     * @Fields LOINGED_URI_COOKIE_NAME : 登录后URI的COOKIE名称
     */
    public static final String LOINGED_URI_COOKIE_NAME = "loginedURI";

    /**
     * @Fields POOL_ID_KEY_IN_MODEL : 消息池在MODLE中的ID
     */
    public static final String POOL_ID_KEY_IN_MODEL = "iaspMsgPoolIdKey";

    /**
     * @Fields URL_TOKEN_NAME URL的令牌，在Request中的名称
     */
    public static final String URL_TOKEN_PARA_NAME = "urlToken";

    /**
     * @Fields URL_TOKEN_NAME URL的令牌，在Cookies中的名称
     */
    public static final String URL_TOKEN_COOKIE_NAME = "pageToken";

    /**
     * @Fields GOBAL_TOKEN_URL_NAME 受令牌控制的URL，在全局配置文件中的键名
     */
    public static final String GOBAL_TOKEN_URL_NAME = "gobal.token.url";

    /**
     * @Fields GOBAL_TOKEN_MAGIC_KEY_NAME 全局配置中MAIGC_KEY的键名
     */
    public static final String GOBAL_TOKEN_MAGIC_KEY_NAME = "gobal.token.magic.key";

    public static final String GOBAL_SECURITY_TIMELIMIT = "gobal.securtiy.timelimit";

    /**
     * @Fields GOBAL_CONTEXT_NAME 全局配置中的上下文
     */
    public static final String GOBAL_CONTEXT_NAME = "gobal.context.name";

    /**
     * @Fields GOBAL_SECURITY_AUTOLOING_NAME 全局配置中关于自动登录的开关键名
     */
    public static final String GOBAL_SECURITY_AUTOLOING_NAME = "gobal.security.autologin";

    /**
     * @Fields GOBAL_SECURITY_AUTOLOING_NAME 全局配置中关于自动登录时，默认登录的账号名键名
     */
    public static final String GOBAL_SECURITY_AUTOLOING_USERNAME_NAME = "gobal.securtiy.autologin.name";

    /**
     * @Fields GOBAL_SMS_PROXY_ONOFF : 短信接口代理开关
     */
    public static final String GOBAL_SMS_PROXY_ONOFF = "gobal.sms.proxy.on-off";

    /**
     * @Fields VIEW_ERROR_PAGE_TIMEOUT 超时页面
     */
    public static final String VIEW_ERROR_PAGE_TIMEOUT = "pagetimeout";

    /**
     * @Fields TABLE_AUTHORITY_FLAG_MENU 权限表中，对象标识字段，菜单标识
     */
    public static final int TABLE_AUTHORITY_FLAG_MENU = 1;

    /**
     * @Fields ROLE_VISIBLE_YES : 角色可见
     */
    public static final int ROLE_VISIBLE_YES = 0;

    /**
     * @Fields ROLE_VISIBLE_NO : 角色不可见
     */
    public static final int ROLE_VISIBLE_NO = 1;

    /**
     * @Fields EXCEL_SHEETTYPE_LIST : excel表格类型：列表型
     */
    public static final int EXCEL_SHEETTYPE_LIST = 1;

    /**
     * @Fields EXCEL_SHEETTYPE_FORM : excel表格类型：表单型
     */
    public static final int EXCEL_SHEETTYPE_FORM = 2;

    /**
     * @Fields EXCEL_SHEETTYPE_FORM_NAME : excel表格类型：表单名字型
     */
    public static final int EXCEL_SHEETTYPE_FORM_NAME = 3;

    /**
     * @Fields EXCEL_ERROR : 导入失败
     */
    public static final int EXCEL_ERROR = -1;

    /**
     * @Fields EXCEL_ERROR_AND_CORRECT : 导入失败和成功（所有的）
     */
    public static final int EXCEL_ERROR_AND_CORRECT = 0;

    /**
     * @Fields EXCEL_CORRECT : 导入成功
     */
    public static final int EXCEL_CORRECT = 1;

    /**
     * @Fields EXCEL_CHECK_TYPE_FIRST : excel初始行
     */
    public static final int EXCEL_CHECK_TYPE_FIRST = 1;

    /**
     * @Fields EXCEL_CHECK_TYPE_LAST : excel结束行
     */
    public static final int EXCEL_CHECK_TYPE_LAST = 2;

    /**
     * @Fields NO : 否（0）
     */
    public static final int NO = 0;

    /**
     * @Fields YES : 是（1）
     */
    public static final int YES = 1;

    /**
     * @Fields BILL_GATHER_STATE_NOT_PASS : 未审核通过
     */
    public static final int BILL_GATHER_STATE_NOT_PASS = 0;

    /**
     * @Fields BILL_GATHER_STATE_PASS : 审核通过
     */
    public static final int BILL_GATHER_STATE_PASS = 1;

    /**
     * @Fields VALIDATE_NUMEL : (验证码个数)
     */
    public static final int VALIDATE_NUMBER = 6;

    /**
     * @Fields CMS_CHANNELID_NOTICE : 公告
     */
    public static final int CMS_CHANNELID_NOTICE = 84;

    /**
     * @Fields CMS_CHANNELID_NEWS : 新闻67
     */
    public static final int CMS_CHANNELID_NEWS = 67;

    /**
     * @Fields CMS_CHANNELID_SPECIAL : 专题68
     */
    public static final int CMS_CHANNELID_SPECIAL = 68;

    /**
     * @Fields CMS_CHANNELID_PERIODICAL : 期刊81
     */
    public static final int CMS_CHANNELID_PERIODICAL = 81;

    /**
     * @Fields CMS_CHANNELID_WORLD : 恒生世界82（期刊子栏目）
     */
    public static final int CMS_CHANNELID_WORLD = 82;

    /**
     * @Fields CMS_CHANNELID_CLOUD : 恒生金融云83（期刊子栏目）
     */
    public static final int CMS_CHANNELID_CLOUD = 83;

    /**
     * @Fields CMS_CHANNELID_STARTED : 新手入门76
     */
    public static final int CMS_CHANNELID_STARTED = 76;

    /**
     * @Fields CMS_CHANNELID_SUPERIOR : 高手进阶75
     */
    public static final int CMS_CHANNELID_SUPERIOR = 75;

    /**
     * @Fields CMS_CHANNELID_SUPERIOR : 课程预告77
     */
    public static final int CMS_CHANNELID_ADNOTICE = 77;

    /**
     * @Fields CMS_CONTEXT_NAME : 全局配置中的CMS上下文
     */
    public static final String CMS_CONTEXT_NAME = "cms.context.name";

    /**
     * @Fields CMS_CONTEXT_NAME : 全局配置中的IASP上下文
     */
    public static final String IASP_CONTEXT_NAME = "iasp.context.name";

    /**
     * @Fields CMS_CONTEXT_NAME : 全局配置中的CMS img上下文
     */
    public static final String CMS_CONTEXT_IMG = "cms.context.img";

    /**
     * @Fields CMS_TYPE_ID_ARTICLE : 学堂课程类型-文章（1）
     */
    public static final int CMS_TYPE_ID_ARTICLE = 1;

    /**
     * @Fields CMS_TYPE_ID_VEDIO : 学堂课程类型-视频（2）
     */
    public static final int CMS_TYPE_ID_VEDIO = 2;

    /**
     * @Fields defaultCompanyId : 默认公司的ID
     */
    public static final String DEFAULT_COMPANYID = "735fdc36-fa99-11e3-9cf8-005056827357";

    /**
     * @Fields IS_VALIDATE_FALSE : 是否验证：否（0）
     */
    public static final int IS_VALIDATE_FALSE = 0;

    /**
     * @Fields IS_VALIDATE_TRUE : 是否验证：是（1）
     */
    public static final int IS_VALIDATE_TRUE = 1;

    /**
     * @Fields TRADE_CATEGORY : 交易品种
     */
    public static final String TRADE_CATEGORY = "交易品种";

    /**
     * @Fields CMS_PER_PAGE_COUNT : cms每页6条
     */
    public static final int CMS_PER_PAGE_COUNT = 6;

    /**
     * @Fields CMS_PER_PAGE_STUDYCOUNT : cms学堂列表每页5条
     */
    public static final int CMS_PER_PAGE_STUDYCOUNT = 5;

    /**
     * @Fields COOKIE_MOBILE_KEY : 储存在cookie中的手机号key
     */
    public static final String COOKIE_MOBILE_KEY = "MO_BILE";

    /**
     * @Fields COOKIE_MOBILE_VALICODE_KEY : 储存在cookie中的手机校验码key
     */
    public static final String COOKIE_MOBILE_VALICODE_KEY = "MO_VALI";

    /**
     * @Fields MESSGAE_TYPE_INFO : 消息
     */
    public static final int MESSGAE_TYPE_INFO = 0;

    /**
     * @Fields MESSGAE_TYPE_INFO : 警告
     */
    public static final int MESSGAE_TYPE_WARNING = 1;

    /**
     * @Fields MESSGAE_TYPE_ERROR : 错误
     */
    public static final int MESSGAE_TYPE_ERROR = 2;

    /**
     * @Fields BILL_CHANGE_NUM : 账单中合同期总成交金额中-9999.99转换成"/"
     */
    public static final double BILL_CHANGE_NUM = -9999.99;

    /**
     * 用户激活=1
     */
    public static final int USER_ACTIVE = 1;

    /**
     * @Fields CMS_STATUS : cms那边审核通过状态为2
     */
    public static final byte CMS_STATUS = 2;

    /**
     * @Fields IMG_WIDTH : 图片的宽
     */
    public static final int IMG_WIDTH = 120;

    /**
     * ihmos version
     */
    public static final String GOBAL_IHOMS_VERSION = "gobal.ihoms.version";

    /**
     * @Fields ANONYMITY_USER : 游客
     */
    public static final String ANONYMITY_USER = "anonymity";

    /**
     * @Fields L0ADBY_XML : 根据原来的xml来下载客户端版本的类型
     */
    public static final int L0ADBY_XML = 4;

    /**
     * @Fields ACCOUNT_TYPE_A : 账户类型：a股
     */
    public static final int ACCOUNT_TYPE_A = 1;

    /**
     * @Fields ACCOUNT_TYPE_FUTURES : 账户类型：期货
     */
    public static final int ACCOUNT_TYPE_FUTURES = 2;
}
