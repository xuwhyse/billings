package com.awhyse.gateway;

import com.awhyse.gateway.server.MkGWClientI;
import com.awhyse.gateway.server.MkGWMsgReciver;
import com.awhyse.gateway.server.impl.MkGWClientIMpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whyse
 * on 2017/8/7 10:26
 */
public class TestMain {
    public static void main(String[] args) {

//        MkGWMsgReciver mkGWMsgReciver = new MkGWMsgReciverImpl();
        MkGWClientI mkGWClientI = new MkGWClientIMpl();

        //192.168.4.91;191    10048  10026us 10028hk
        String ip = "192.168.4.91";//127.0.0.1
        int port = 10028;
        String account = "xumin";
        String pwd = "admin";
        int flag = mkGWClientI.tryConnect(ip, port, account, pwd, new MkGWMsgReciver() {
            @Override
            public void onMsg(Object msg) {
//                System.out.println(msg);
            }
        });
        if(flag==1){
            List<String> list = new ArrayList<>(5);
//            list.add("300250.SZ");
//            list.add("601600.SH");
            list.add("00700.HK");
            mkGWClientI.reqSubSymbols(list);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mkGWClientI.reqUnSubSymbols(list);
        }
    }
}
