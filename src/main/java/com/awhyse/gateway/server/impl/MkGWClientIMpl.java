package com.awhyse.gateway.server.impl;

import com.awhyse.gateway.MsgPackLiteDataClient;
import com.awhyse.gateway.server.MkGWClientI;
import com.awhyse.gateway.server.MkGWMsgReciver;

import java.util.List;

/**
 * Created by whyse
 * on 2017/8/7 10:30
 */
public class MkGWClientIMpl implements MkGWClientI {

    MkGWMsgReciver mkGWMsgReciver;
    MsgPackLiteDataClient msgPackLiteDataClient;

    //================================================================


    @Override
    public int reqSubSymbols(List<String> listSymbol) {
        return reqCommonSymbolAPI("API=SUBSCRIBE",listSymbol);
    }
    /**
     *
     * @param api  API=SUBSCRIBE 例子
     * @param listSymbol
     * @return
     */
    private int reqCommonSymbolAPI(String api, List<String> listSymbol) {
        if(listSymbol==null || listSymbol.size()==0) {
            return -1;
        }
        StringBuffer symbolSB = new StringBuffer("");
        for(int i=0;i<listSymbol.size()-1;i++){
            symbolSB.append(listSymbol.get(i)+";");
        }
        symbolSB.append(listSymbol.get(listSymbol.size()-1));

        StringBuffer sb = new StringBuffer("");
        //"API=SUBSCRIBE"
        sb.append(api).append("|Symbol=").append(symbolSB.toString());
        String subscribeStr = sb.toString();
        subscribeStr = subscribeStr + "|Hash=" + subscribeStr.hashCode();//API命令拼接完成

        msgPackLiteDataClient.sendRequest(subscribeStr);
        return 0;
    }

    @Override
    public int reqUnSubSymbols(List<String> listSymbol) {
        return reqCommonSymbolAPI("API=UNSUBSCRIBE",listSymbol);
    }

    @Override
    public int tryConnect(String ip, int port, String account, String pwd, MkGWMsgReciver mkGWMsgReciver) {
        this.mkGWMsgReciver = mkGWMsgReciver;
        msgPackLiteDataClient = new MsgPackLiteDataClient();
        msgPackLiteDataClient.mkGWMsgReciver = this.mkGWMsgReciver;
        msgPackLiteDataClient.gatewayHost = ip;
        msgPackLiteDataClient.gatewayPort = port;
        msgPackLiteDataClient.run();
        return 1;
    }
}
