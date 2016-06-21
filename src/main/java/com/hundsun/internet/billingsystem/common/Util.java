package com.hundsun.internet.billingsystem.common;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/** 公共工具类.
 * @author: shangke
 * @since: 2014年2月18日 下午1:53:26
 * @history:
 */
public class Util {
    /**
     * 获取日期字符串.
     * @param date 需要转换成字符串的日期类型
     * @return yyyyMMdd 日期字符串格式
     * @create: 2014年2月18日 下午1:55:25 shangke
     * @history:
     */
    public static String getDateString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * @Fields NUMBERS : 数字字符
     */
    public static final char[] NUMBERS = new char[] { '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9' };

    /**
     * 生成纯数字的随机验证码.
     * @param count 位数
     * @return 随机验证码
     * @create: 2014年2月20日 下午14:16:25 xusheng
     */
    public static String randomCode(int count) {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            code.append(NUMBERS[random.nextInt(NUMBERS.length) % NUMBERS.length]);
        }
        return code.toString();
    }

    /**
     * 将日历对象转化为yyyy-MM-dd格式字符串.
     * @param calendar 日历对象
     * @return yyyy-MM-dd格式字符串.
     * @create: 2014年2月27日 上午10:59:47 shangke
     * @history:
     */
    // public static String calendar2DateString(Calendar calendar) {
    // // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // // return sdf.format(calendar.getTime());
    // return com.hundsun.internet.iasp.db.core.common.Util
    // .calendar2DateString(calendar);
    // }

    /**
     * 将日历对象转化为"yyyy-MM-dd HH:mm:dd格式字符串.
     * @param calendar 日历对象
     * @return "yyyy-MM-dd HH:mm:dd格式字符串.
     * @create: 2014年2月27日 上午10:59:47 shangke
     * @history:
     */
    // public static String calendar2DateTimeString(Calendar calendar) {
    // // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
    // // return sdf.format(calendar.getTime());
    // return com.hundsun.internet.iasp.db.core.common.Util
    // .calendar2DateTimeString(calendar);
    // }

    /**
     * 将yyyy-MM-dd格式字符串转化为日历对象.
     * @param dateString yyyy-MM-dd格式字符串
     * @return 日历对象
     * @create: 2014年2月27日 上午11:04:50 shangke
     * @history:
     */
    public static Calendar dateString2Calendar(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(dateString);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    /**将yyyy-MM-dd 00:00:00格式字符串转化为日历对象.
     * @param dateString 日期字符串
     * @return Calendar
     * @create: 2014年5月22日 上午11:34:05 hanmeng
     * @history:
     */
    public static Calendar string2Calendar(String dateString) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date date;
        try {
            date = sdf.parse(dateString);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    /**
     * 将日历对象转化为yyyy-MM-dd格式字符串.
     * @param calendar 日历对象
     * @return yyyy-MM-dd格式字符串.
     * @create: 2014年2月27日 上午10:59:47 shangke
     * @history:
     */
    public static String calendar2DateString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(calendar.getTime());
    }

    /**将string日期类型转换为Date类型 并格式化成yyyy-MM-dd.
     * @param dateString String类型的时间
     * @return Date
     * @create: 2014年5月23日 上午8:56:17 hanmeng
     * @history:
     */
    public static Date string2DateWithOutHHmmss(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将string日期类型转换为Date类型 并格式化成yyyy-MM-dd HH:mm:ss.
     * @param dateString String类型的时间
     * @return Date
     * @create: 2014年5月23日 上午9:11:46 hanmeng
     * @history:
     */
    public static Date string2DateWithHHmmss(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将string日期类型转换为Date类型 并格式化成yyyy-MM.
     * @param dateString String类型的时间
     * @return Date
     * @create: 2014年5月23日 上午9:11:46 hanmeng
     * @history:
     */
    public static Date string2DateWithMM(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将string日期类型格式化成yyyy-MM,返回String.
     * @param dateString String类型的时间
     * @return string
     * @create: 2014年5月23日 上午9:11:46 hanmeng
     * @history:
     */
    public static String timeFormatWithMM(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        try {
            Date date = sdf.parse(dateString);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将Date日期类型转换为String类型 并格式化成yyyy-MM-dd HH:mm:ss.
     * @param date Date型日期
     * @return String
     * @throws ParseException
     * @create: 2014年5月26日 下午3:04:49 hanmeng
     * @history:
     */
    public static String date2StringWithHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        return dateString;
    }

    /**将Date日期类型转换为String类型 并格式化成yyyy-MM-dd.
     * @param date Date型日期
     * @return String
     * @throws ParseException
     * @create: 2014年5月26日 下午3:04:49 hanmeng
     * @history:
     */
    public static String date2StringWithOutHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }

    /**四舍五入 保留1位小数,.
     * @param num double类型数据
     * @return String
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static String keepOneDecimal2String(double num) {

        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return bd2.toString();
    }

    /**四舍五入  保留2位小数.
     * @param num double类型数据
     * @return String
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static String keepTwoDecimal2String(double num) {

        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd2.toString();
    }

    /**四舍五入 保留1位小数 返回double类型数据.
     * @param num double类型数据
     * @return double
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static double keepOneDecimal2Double(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return bd2.doubleValue();
    }

    /**四舍五入 保留2位小数 返回double类型数据.
     * @param num double类型数据
     * @return double
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static double keepTwoDecimal2Double(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd2.doubleValue();
    }

    /**四舍五入 保留1位小数 返回BigDecimal类型数据.
     * @param num double类型数据
     * @return BigDecimal
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static BigDecimal keepOneDecimal2BigDecimal(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
        return bd2;
    }

    /**四舍五入 保留2位小数 返回BigDecimal类型数据.
     * @param num double类型数据
     * @return BigDecimal
     * @create: 2014年7月4日 上午10:21:42 hanmeng
     * @history:
     */
    public static BigDecimal keepTwoDecimal2BigDecimal(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd2;
    }

    /**将double类型转换成BigDecimal.
     * @param num double类型数据
     * @return BigDecimal
     * @create: 2014年7月18日 下午3:59:32 hanmeng
     * @history:
     */
    public static BigDecimal double2BigDecimal(double num) {
        BigDecimal bd = new BigDecimal(String.valueOf(num));
        return bd;
    }

    /**
     * @Fields LOWER : 小写
     */
    private static final String LOWER = ".*[a-z].*";

    /**
     * @Fields UPPER : 大写
     */
    private static final String UPPER = ".*[A-Z].*";

    /**
     * @Fields DIGIT : 字数
     */
    private static final String DIGIT = ".*[0-9].*";

    /**
     * @Fields DIGITS : 多数字
     */
    private static final String DIGITS = ".*[0-9].*[0-9].*";

    /**
     * @Fields SPECIAL : 特殊字符
     */
    private static String SPECIAL = ".*[^a-zA-Z0-9].*";

    /**
     * @Fields SAME : 相同
     */
    private static String SAME = "^(.)\\1+$";

    /**计算密码强度.
     * @param password 密码
     * @return int
     * @create: 2014年7月1日 上午10:40:03 hanmeng
     * @history:
     */
    public static int calculatePwdLevel(String password) {
        if (password == null) {
            return 0;
        }
        if (password.length() < 6) {
            return 0;
        }
        if (password.matches(SAME)) {
            return 1;
        }
        boolean lower = password.matches(LOWER), upper = (password.substring(0,
            1).toLowerCase() + password.substring(1)).matches(UPPER), digit = password
            .matches(DIGIT), digits = password.matches(DIGITS), special = password
            .matches(SPECIAL);
        if (lower && upper && digit || lower && digits || upper && digits
                || special) {
            return 4;
        }
        if (lower && upper || lower && digit || upper && digit) {
            return 3;
        }
        return 2;
    }

    /**
     * 将yyyy-MM-dd HH:mm:dd格式字符串转化为日历对象.
     * @param dateString yyyy-MM-dd HH:mm:dd格式字符串
     * @return 日历对象
     * @throws ParseException 异常
     * @create: 2014年2月27日 上午11:04:50 shangke
     * @history:
     */
    public static Calendar dateTimeString2Calendar(String dateString)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将父类中的值填充到之类.
     * @param parent 父类
     * @param child 子类
     * @create: 2014年2月27日 下午2:43:06 shangke
     * @history:
     */
    public static void putParentBean2ChildBean(Object parent, Object child) {
        // 不需要的自己去掉即可
        if (parent != null && child != null) {
            // 拿到该类
            Class<?> clz = parent.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields) {
                String fieldType = field.getGenericType().toString();
                try {
                    // 拿到该属性的gettet方法
                    Method pm = (Method) parent.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                    Method cm = null;
                    if (fieldType.equals("int")) {
                        cm = (Method) child.getClass().getMethod(
                            "set" + getMethodName(field.getName()), int.class);
                    } else if (fieldType.equals("float")) {
                        cm = (Method) child.getClass()
                            .getMethod("set" + getMethodName(field.getName()),
                                float.class);
                    } else if (fieldType.equals("double")) {
                        cm = (Method) child.getClass().getMethod(
                            "set" + getMethodName(field.getName()),
                            double.class);
                    } else {
                        cm = (Method) child.getClass().getMethod(
                            "set" + getMethodName(field.getName()),
                            Class.forName(fieldType.substring(
                                fieldType.indexOf(" ") + 1).trim()));
                    }

                    // 调用getter方法获取属性值
                    Object val = (Object) pm.invoke(parent);

                    // 调用setter方法设置值
                    cm.invoke(child, val);
                } catch (Exception ex) {
                    // 不做任何处理
                    continue;
                }
            }
        }
    }

    /**把一个字符串的第一个字母大写、效率是最高的.
     * @param fildeName 传递过来的方法名
     * @return String
     * @throws Exception 抛出的异常
     * @create: 2014年1月9日 上午9:57:37 shangke
     * @history: 2014年1月9日 上午9:57:37 shangke
     */
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 自动去除BEAN对象中的String类型的前后空格.
     * @param object 对象
     * @create: 2014年2月27日 下午5:23:18 shangke
     * @history:
     */
    public static void autoTrim(Object object) {
        // 不需要的自己去掉即可
        if (object != null) {
            // 拿到该类
            Class<?> clz = object.getClass();
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields) {
                String fieldType = field.getGenericType().toString();
                if (fieldType.equals("class java.lang.String")) {
                    try {
                        // 拿到该属性的gettet方法
                        Method pm = (Method) object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                        Method cm = (Method) object.getClass().getMethod(
                            "set" + getMethodName(field.getName()),
                            Class.forName(fieldType.substring(
                                fieldType.indexOf(" ") + 1).trim()));

                        // 调用getter方法获取属性值
                        String val = (String) pm.invoke(object);
                        val.trim();
                        // 调用setter方法设置值
                        cm.invoke(object, val);
                    } catch (Exception ex) {
                        // 不做任何处理
                        continue;
                    }
                }
            }
        }
    }

    /**MD5加密.
     * @param s 传过来的参数
     * @return String
     * @create: 2014年5月7日 下午1:20:23 hanmeng
     * @history:
     */
    public static String encodeMD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**获取request中对应name的cookie值.
     * @param name cookie的name
     * @param request HttpServletRequest
     * @return String 找不到的时候返回""
     * @create: 2014年2月21日 下午4:05:04 linyy
     * @history:
     */
    public static String getCookieByName(String name, HttpServletRequest request) {
        String value = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    value = cookies[i].getValue();
                }
            }
        }

        return value;
    }

    /**检测字符是否含有特殊字符.
     * @param chars 字符
     * @return boolean
     * @create: 2014年7月17日 下午6:25:24 hanmeng
     * @history:
     */
    public static boolean checkSpechars(String chars) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(chars);
        String newChars = m.replaceAll("").trim();
        // 如果新字符长度小于原字符长度，说明含有特殊字符
        if (newChars.length() < chars.length()) {
            return true;
        }
        return false;
    }

    /**
     *破除逆向代理等获取不到正确ip
     * @param request
     * @return
     * @create: 2014-5-8 上午8:36:18 xunmin
     * @history:
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 将map中的信息按照json输出
     * @author xunmin
     * @createTime 上午11:19:56
     * @param map
     * @param request
     * @param response
     */
	public static void responseToClientNow(Map<String, String> map,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8"); 
        ObjectMapper objectMapper = new ObjectMapper(); 
        ServletOutputStream out=null;
        try { 
            out = response.getOutputStream(); 
            JsonGenerator jsonGenerator = objectMapper.getJsonFactory() 
            .createJsonGenerator(out, JsonEncoding.UTF8); 
            jsonGenerator.writeObject(map); 
            
        } catch (IOException e) { 
        	e.printStackTrace(); 
        }finally{
        	try {
				out.flush();
				out.close(); 
			} catch (IOException e) {
			}
        }
	}
	
}
