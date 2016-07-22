/**
 * @Title MapUtil.java
 * @Description map和query string之间转换
 * @author xusheng
 * @date 2014年2月7日
 * @version V1.0
 */
package com.billings.billingsystem.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xusheng on 14-1-28.
 */
public final class MapUtil {

    /**
     * 私有构造函数，类不可实例化.
     */
    private MapUtil() {

    }

    /**
     * 将Map转化为query string.
     * @param map key-value对
     * @return query string
     */
    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key,
                    "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value,
                    "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                    "This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 将query string转化为Map.
     * @param input query String
     * @return Map
     */
    public static Map<String, String> stringToMap(String input) {
        Map<String, String> map = new HashMap<String, String>();

        if (input == null || input.length() == 0) {
            return map;
        }

        String[] nameValuePairs = input.split("&");
        for (String nameValuePair : nameValuePairs) {
            String[] nameValue = nameValuePair.split("=");
            try {
                map.put(
                    URLDecoder.decode(nameValue[0], "UTF-8"),
                    nameValue.length > 1 ? URLDecoder.decode(nameValue[1],
                        "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                    "This method requires UTF-8 encoding support", e);
            }
        }

        return map;
    }
}
