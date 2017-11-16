package com.awhyse.gateway.server.impl;

import com.awhyse.gateway.common.Quote;
import com.awhyse.gateway.server.MkGWClientI;
import com.awhyse.gateway.server.MkGWMsgReciver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whyse
 * on 2017/8/7 10:48
 */
public class MkGWMsgReciverImpl implements MkGWMsgReciver {

    MkGWClientI mkGWClientI;

    public MkGWMsgReciverImpl(MkGWClientI mkGWClientI) {
        this.mkGWClientI = mkGWClientI;
    }

    //========================================================

    @Override
    public void onActive() {
        mkGWClientI.reqLogin();
//        List<String> list = new ArrayList<>(5);
//            list.add("300250.SZ");
//        list.add("601600.SH");
////            list.add("00700.HK");
//        mkGWClientI.reqSubSymbols(list);
    }
    @Override
    public void rspLogin(int state, String rspMsg) {
        if(state==1){
            List<String> list = new ArrayList<>(5);
            list.add("300250.SZ");
            list.add("601600.SH");
            list.add("00700.HK");
            mkGWClientI.reqSubSymbols(list);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            mkGWClientI.reqUnSubSymbols(list);
        }
    }

    @Override
    public void onQupte(Quote quote) {
        System.err.println(quote.toString());
    }

    @Override
    public void onMsg(Object msg) {

    }

}
