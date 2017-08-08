package com.awhyse.gateway.server;

import java.util.List;

/**
 * java gateway 的接口
 * Created by whyse
 * on 2017/8/4 17:31
 */
public interface MkGWClientI {
    int reqSubSymbols(List<String> listSymbol);
    int reqUnSubSymbols(List<String> listSymbol);
    /**
     * @param ip
     * @param port
     * @param account
     * @param pwd
     * @param mkGWMsgReciver
     * @return
     */
    int tryConnect(String ip, int port, String account, String pwd, MkGWMsgReciver mkGWMsgReciver);
}
