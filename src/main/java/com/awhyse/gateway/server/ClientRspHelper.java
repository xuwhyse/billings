package com.awhyse.gateway.server;

/**
 * Created by whyse
 * on 2017/8/9 11:46
 */
public class ClientRspHelper {
    public static void rspLogin(MkGWMsgReciver mkGWMsgReciver, String msg) {
        int state = Integer.parseInt(msg);
        String rspMsg = null;
        if(state == 1){
            rspMsg = "登录成功";
        }else if(state == -1){
            rspMsg = "用户名或者密码错误";
        }else if(state == -2){
            rspMsg = "其他用户占用";
        }else if(state == 0){
            rspMsg = "登录中";
        }else{
            rspMsg = "未知错误";
        }
        mkGWMsgReciver.rspLogin(state,rspMsg);
    }
}
