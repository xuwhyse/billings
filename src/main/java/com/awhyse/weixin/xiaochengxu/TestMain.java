package com.awhyse.weixin.xiaochengxu;

import com.alibaba.fastjson.JSON;
import com.awhyse.util.decEncCode.SHAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by whyse
 * on 2017/2/7 16:06
 */
public class TestMain {
    static Logger loger = LoggerFactory.getLogger(TestMain.class);
    public static void main(String[] args) {
        signatureTest();
    }
    /**
     * signature = sha1( rawData + sessionkey )
     * sessionkey 本次登录的 会话密钥,重连就会变
     * rawData 用于计算签名
     * @author whyse
     * @date 2017/2/7 16:08
     */
    private static void signatureTest() {
        Map<String,Object> map = new LinkedHashMap<>(8);
        map.put("nickName","Band");
        map.put("gender",1);
        map.put("language","zh_CN");
        map.put("city","Guangzhou");
        map.put("province","Guangdong");
        map.put("country","CN");
        map.put("avatarUrl","http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0");

        String sessionkey = "HyVFkGl5F5OQWJZZaNzBBg==";
        String key = JSON.toJSONString(map)+sessionkey;
        System.out.println(key);


        key = SHAUtil.hex_sha1(key);
//        System.out.println(key);

        loger.debug(key);
    }
}
