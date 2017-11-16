package com.awhyse.gateway.server;

import com.alibaba.fastjson.JSON;
import com.awhyse.gateway.server.impl.MkGWClientIMpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by whyse
 * on 2017/8/9 11:46
 */
public class ClientReqHelper {

    /**
     * 将客户端的账户名密码，发起请求
     * @param mkGWClientIMpl
     */
    public static void reqLogin(MkGWClientIMpl mkGWClientIMpl) {
        Map<String,Object> map = new HashMap(2);
        map.put("account",mkGWClientIMpl.account);
        map.put("pwd",mkGWClientIMpl.pwd);
        //服务器需要验证你的信息
        StringBuffer sb = new StringBuffer("");
        sb.append("API=LOGIN").append("|Symbol=").append(JSON.toJSONString(map));
        String subscribeStr = sb.toString();
        subscribeStr = subscribeStr + "|Hash=" + subscribeStr.hashCode();//API命令拼接完成

        mkGWClientIMpl.msgPackLiteDataClient.sendRequest(subscribeStr);
    }
}
