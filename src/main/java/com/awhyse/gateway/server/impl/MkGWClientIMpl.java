package com.awhyse.gateway.server.impl;

import com.awhyse.gateway.MsgPackLiteDataClient;
import com.awhyse.gateway.server.ClientReqHelper;
import com.awhyse.gateway.server.ClientRspHelper;
import com.awhyse.gateway.server.MkGWClientI;
import com.awhyse.gateway.server.MkGWMsgReciver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by whyse
 * on 2017/8/7 10:30
 */
public class MkGWClientIMpl implements MkGWClientI {

    Logger logger = LoggerFactory.getLogger(MkGWClientIMpl.class);
    MkGWMsgReciver mkGWMsgReciver;
    public MsgPackLiteDataClient msgPackLiteDataClient;
    public String account;
    public String pwd;

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
    public int reqConnect(String ip, int port, String account, String pwd, MkGWMsgReciver mkGWMsgReciver) {
        this.mkGWMsgReciver = mkGWMsgReciver;
        msgPackLiteDataClient = new MsgPackLiteDataClient();
        msgPackLiteDataClient.mkGWMsgReciver = this.mkGWMsgReciver;
        msgPackLiteDataClient.mkGWClient = this;
        msgPackLiteDataClient.gatewayHost = ip;
        msgPackLiteDataClient.gatewayPort = port;
        this.account = account;
        this.pwd = pwd;
        msgPackLiteDataClient.run();
        return 1;
    }

    @Override
    public int reqLogin() {
        ClientReqHelper.reqLogin(this);
        return 0;
    }

    /**
     * 处理上游给出的命令
     * @param in
     */
    public void processOrder(String in) {
        if(in==null){
            logger.error("未知命令");
            return;
        }
        logger.info(in);
        String strDataType = null;
        String symbols = null;
        String strMarket = null;
        String[] in_arr = in.split("\\|");
        for (String str : in_arr) {
            if (str.startsWith("API=")) {
                strDataType = str.substring(4);
            }
            if (str.startsWith("Symbol=")) {
                symbols = str.substring(7);
            }
            if (str.startsWith("Market=")) {
                strMarket = str.substring(7);
            }
        }
        //-----------解析完毕----------------------
        if(strDataType.equals("GOTOLOGIN")){
            ClientReqHelper.reqLogin(this);
        }else if(strDataType.equals("RSPLOGIN")){
            ClientRspHelper.rspLogin(mkGWMsgReciver,symbols);
        }
    }
}
