package com.hundsun.internet.billingsystem.common.velocity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hundsun.internet.billingsystem.common.GobalConfig;


/**velocity工具类 .
 * @author: linyy
 * @since: 2014年5月8日 下午2:54:59
 * @history:
 */
public class VelocityTool {
    /**
     * @Fields logger : 日志
     */
    private Logger logger = Logger.getLogger(ValueRange.class);

    /**
     * @Fields valueRange : 值域
     */
    @Autowired
    private ValueRange valueRange;

    /**替换标记以正常显示.
     * @param input input
     * @return 结果
     * @create: 2014年7月11日 上午11:05:02 linyy
     * @history:
     */
    public String replaceTag(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {
            c = input.charAt(i);
            switch (c) {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return (filtered.toString());
    }

    /**是否包含特殊字符.
     * @param input input
     * @return 是否
     * @create: 2014年7月11日 上午10:49:03 linyy
     * @history:
     */
    public boolean hasSpecialChars(String input) {
        boolean flag = false;
        if ((input != null) && (input.length() > 0)) {
            char c;
            for (int i = 0; i <= input.length() - 1; i++) {
                c = input.charAt(i);
                switch (c) {
                    case '>':
                        flag = true;
                        break;
                    case '<':
                        flag = true;
                        break;
                    case '"':
                        flag = true;
                        break;
                    case '&':
                        flag = true;
                        break;
                    default:
                        break;
                }
            }
        }
        return flag;
    }

    // ******************************************//
    // ******************************************//
    // ******************************************//
    // **************以下不可用*******************//
    // ******************************************//
    // ******************************************//
    // ******************************************//

    /**获得select中的某一个bean对应的option选项.
     * @param obj bean
     * @param key 字段名
     * @param value 当前选项的值
     * @param type 转换值域的类型
     * @return <option value="">...<\/option>
     * @create: 2014年5月8日 下午5:23:17 linyy
     * @history:
     */
    public String option(Object obj, String key, String value, String type) {
        Object o = this.getProperty(obj, key);
        String selectvalue = "";
        if (o != null) {
            selectvalue = o.toString();
        }
        StringBuffer retValue = new StringBuffer();
        retValue.append("<option value=\"").append(selectvalue).append("\"");
        if (selectvalue.equals(value)) {
            retValue.append(" selected ");
        }
        retValue.append(">");
        retValue.append(changeValue(type, selectvalue));
        retValue.append("</option>");
        return retValue.toString();
    }

    /**checkbox中每行的label，checkbox.
     * @param obj bean
     * @param key 字段名
     * @param value 当前选项的值（逗号分隔）
     * @param type 转换值域的类型
     * @param attr checkbox的属性
     * @param labelClass label的属性
     * @return <label><input type="checkbox"><\/input><\/label>
     * @create: 2014年5月9日 上午9:47:33 linyy
     * @history:
     */
    public String checkbox(Object obj, String key, String value, String type,
            String attr, String labelClass) {
        Object o = this.getProperty(obj, key);
        String item = "";
        if (o != null) {
            item = o.toString();
        }
        String[] values = value.split(",");
        StringBuffer retValue = new StringBuffer();
        retValue.append("<label class=\"").append(labelClass).append("\">");
        retValue.append("<input type=\"checkbox\" value = \"");
        retValue.append(item).append("\" ").append(attr);
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(item)) {
                retValue.append(" checked ");
                break;
            }
        }
        retValue.append(">");
        retValue.append(changeValue(type, item));
        retValue.append("</label>");

        return retValue.toString();
    }

    /**radio中每行的label，radio.
     * @param obj bean
     * @param key 字段名
     * @param value 当前选项的值（逗号分隔）
     * @param type 转换值域的类型
     * @param attr checkbox的属性
     * @param labelClass label的属性
     * @return <label><input type="radio"><\/input><\/label>
     * @create: 2014年5月9日 上午10:04:31 linyy
     * @history:
     */
    public String radio(Object obj, String key, String value, String type,
            String attr, String labelClass) {
        Object o = this.getProperty(obj, key);
        String item = "";
        if (o != null) {
            item = o.toString();
        }
        StringBuffer retValue = new StringBuffer();
        retValue.append("<label class=\"").append(labelClass).append("\">");
        retValue.append("<input type=\"radio\" value=\"").append(item);
        retValue.append("\" ").append(attr);
        if (item.equals(value)) {
            retValue.append(" checked ");
        }
        retValue.append(">");
        retValue.append(changeValue(type, item));
        retValue.append("</label>");

        return retValue.toString();
    }

    /**给用户value的显示.
     * @param type 值域类型
     * @param value 需要转换的值
     * @return 页面显示值
     * @create: 2014年5月9日 上午10:34:12 linyy
     * @history:
     */
    public String changeValue(String type, String value) {
        String retValue = "";
        // 如果type是特殊的类型，先处理
        // 如果type不为空，去valueRange.properties中寻找
        // 如果是空，原样返回
        if (type.equals("WEEK")) {
            retValue = value + "周";
        } else if (type != null && !"".equals(type)) {
            retValue = valueRange.value(type, value);
        } else {
            retValue = value;
        }
        return retValue;
    }

    /**通过反射获得属性值.
     * @param obj 对象
     * @param key 属性名
     * @return 属性值
     * @create: 2014年5月8日 下午2:57:20 linyy
     * @history:
     */
    public Object getProperty(Object obj, String key) {
        Class clazz = null;
        try {
            clazz = obj.getClass();
        } catch (Exception e) {
            logger.error("VelocityTool getProperty obj不是一个类", e);
        }
        Method method = null;
        try {
            method = clazz.getMethod("get" + firstLetterUp(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object o = null;
        try {
            o = method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**首字母大写.
     * @param str 字符串
     * @return 字符串
     * @create: 2014年5月8日 下午3:07:24 linyy
     * @history:
     */
    public static String firstLetterUp(String str) {
        if (str != null && !"".equals(str)) {
            return str.substring(0, 1).toUpperCase()
                    + str.substring(1, str.length());
        } else {
            return str;
        }
    }

    // ******************************************//
    // ******************************************//
    // ******************************************//
    // **************以上不可用*******************//
    // ******************************************//
    // ******************************************//
    // ******************************************//

    /**
     * 将str 转化成 指定 编码输出
     * @param str
     * @param type
     * @return
     * @create: 2014-7-18 下午9:37:11 xunmin
     * @history:
     */
    public static String urlencode(String str, String type) {

        if (str != null && !"".equals(str) && type != null && !"".equals(type)) {
            try {
                return URLEncoder.encode(str, type);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "参数传错了";
    }

    /**获取ihoms全局变量的url.
     * @return String
     * @create: 2014年9月3日 上午10:46:05 hanmeng
     * @history:
     */
    public String ihomsUrl() {
        return GobalConfig.getValue("ihoms.context.name");
    }
    /**
     * 获取iasp.property中的配置信息
     * @param propertieName  这个是里面的name
     * @return
     */
    public String getGlobalSet(String propertieName) {
        return GobalConfig.getValue(propertieName);
    }
}
