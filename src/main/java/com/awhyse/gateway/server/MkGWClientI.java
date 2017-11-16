package com.awhyse.gateway.server;

import java.util.List;

/**
 * java gateway 的接口
 * Created by whyse
 * on 2017/8/4 17:31
 */
public interface MkGWClientI {
    /**
     * @param ip
     * @param port
     * @param account
     * @param pwd
     * @param mkGWMsgReciver
     * @return
     */
    int reqConnect(String ip, int port, String account, String pwd, MkGWMsgReciver mkGWMsgReciver);

    /**
     * 连接成功后，可以请求login
     * @return
     */
    int reqLogin();
    /**
     * 批量订阅
     * @param listSymbol
     * @return
     */
    int reqSubSymbols(List<String> listSymbol);
    int reqUnSubSymbols(List<String> listSymbol);
}
