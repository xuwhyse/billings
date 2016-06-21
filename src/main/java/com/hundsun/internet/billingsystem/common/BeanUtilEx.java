package com.hundsun.internet.billingsystem.common;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.SqlDateConverter;

public class BeanUtilEx extends BeanUtils {

    static {
        // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);
        // ConvertUtils.register(new SqlTimestampConverter(),
        // java.sql.Timestamp.class);
        // 注册util.date的转换器，转化到date的时候将调用这个转化器
        ConvertUtils.register(new UtilDateConverter(), java.util.Date.class);
    }

    public static void copyProperties(Object target, Object source) {
        // update bu zhuzf at 2004-9-29
        // 支持对日期copy
        try {
            BeanUtils.copyProperties(target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**用一句话描述这个方法的作用.
     * @param args
     * @create: 2014-6-25 下午7:44:55 xunmin
     * @history:
     */
    public static void main(String[] args) {
    }

}

class UtilDateConverter implements Converter {

	public Object convert(Class arg0, Object arg1) {
		 String p = (String) arg1;
	        if (p == null || p.trim().length() == 0) {
	            return null;
	        }
	        try {
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            return  df.parse(p.trim());
	        } catch (Exception e) {
	            try {
	                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	                return df.parse(p.trim());
	            } catch (Exception ex) {
	                return null;
	            }
	        }
	}
}
