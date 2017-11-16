package com.awhyse.gateway;

import com.awhyse.gateway.server.MkGWClientI;
import com.awhyse.gateway.server.MkGWMsgReciver;
import com.awhyse.gateway.server.impl.MkGWClientIMpl;
import com.awhyse.gateway.server.impl.MkGWMsgReciverImpl;

/**
 * Created by whyse
 * on 2017/8/7 10:26
 */
public class TestMain {
    public static void main(String[] args) {

        MkGWClientI mkGWClientI = new MkGWClientIMpl();
        MkGWMsgReciver mkGWMsgReciver = new MkGWMsgReciverImpl(mkGWClientI);
        //172.16.1.3
        //192.168.4.91;191    10048  10026us 10028hk
        String ip = "127.0.0.1";//127.0.0.1
        int port = 10048;
        String account = "xumin";
        String pwd = "admin";
        int flag = mkGWClientI.reqConnect(ip, port, account, pwd, mkGWMsgReciver);
    }
}
