package com.awhyse.gateway.common;

import com.awhyse.gateway.transport.FDTFields;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by whyse
 * on 2017/8/7 18:34
 */
public class Quote {
    private static final Logger log = LoggerFactory.getLogger(Quote.class);
    public int sourceId = 1;
    /**
     * 55
     */
    public String symbol;
    /**
     * 118
     */
    public double bid;
    /**
     * 120
     */
    public double ask;
    public double bidVol;
    public double askVol;
    /**
     * 31
     */
    public double last;
    public double lastVol;
    /**
     * 13 交易额
     */
    public double turnover;
    /**
     * 14 量
     */
    public double totalVolume;
    /**
     * 32
     */
    public double high;
    /**
     * 33
     */
    public double low;
    /**
     * 25
     */
    public double open;
    /**
     * 40
     */
    public double preClose;
//    /**
//     *49 涨停
//     */
//    public double highLimit;
//    /**
//     * 48 跌停
//     */
//    public double lowLimit;
    /**
     * 23
     * 111851000 : 11:18:51.000
     */
    public long timeStamp;
    public long timeRec;
    /**
     * 20170808: 2017:08:08
     */
    public long tradeDate;
//    public boolean stale;
    /**
     * 18
     */
    public List<Double> bids;
    /**
     * 19
     */
    public List<Long> bidVols;
    /**
     * 20
     */
    public List<Double> asks;
    /**
     * 21
     */
    public List<Long> askVols;

    //==================================================
    public static Quote getQuoteByMap(HashMap<Integer, Object> in) {
        Quote item = new Quote();
        String symbol = new String((byte[])in.get(FDTFields.WindSymbolCode), CharsetUtil.UTF_8);
        item.symbol = symbol;
        item.timeRec = System.currentTimeMillis();
        if(in.get(FDTFields.BidPrice)!=null)
            item.bid = (long) in.get(FDTFields.BidPrice)*1.0/10000;
        if(in.get(FDTFields.AskPrice)!=null)
            item.ask = (long) in.get(FDTFields.AskPrice)*1.0/10000;
//        item.bidVol = (double) in.get(FDTFields.BidVolume);
//        item.askVol = (double) in.get(FDTFields.AskVolume);
        if(in.get(FDTFields.Last)!=null)
            item.last = (long)in.get(FDTFields.Last)*1.0/10000;//31：last
//        item.lastVol = (double) in.get(FDTFields.v);
        if(in.get(FDTFields.Turnover)!=null)
            item.turnover = (long) in.get(FDTFields.Turnover);//13
        if(in.get(FDTFields.Volume)!=null)
            item.totalVolume = (long) in.get(FDTFields.Volume);//14

        if(in.get(FDTFields.High)!=null)
            item.high = (long)in.get(FDTFields.High)*1.0/10000;//32
        if(in.get(FDTFields.Low)!=null)
            item.low = (long) in.get(FDTFields.Low)*1.0/10000;//33
        if(in.get(FDTFields.Open)!=null)
            item.open = (long) in.get(FDTFields.Open)*1.0/10000;//25
        if(in.get(FDTFields.PreClose)!=null)
            item.preClose = (long) in.get(FDTFields.PreClose)*1.0/10000;//40

        if(in.get(FDTFields.BidPriceArray)!=null) {
            List<Long> temps = (List<Long>) in.get(FDTFields.BidPriceArray);
            List<Double> listTmep = new ArrayList<>(temps.size());
            for(int i=0;i<temps.size();i++)
                listTmep.add(temps.get(i)*1.0/10000);
            item.bids = listTmep;
        }
        if(in.get(FDTFields.BidVolumeArray)!=null) {
            item.bidVols = (List<Long>) in.get(FDTFields.BidVolumeArray);
        }
        if(in.get(FDTFields.AskPriceArray)!=null) {
            List<Long> temps = (List<Long>) in.get(FDTFields.AskPriceArray);
            List<Double> listTmep = new ArrayList<>(temps.size());
            for(int i=0;i<temps.size();i++)
                listTmep.add(temps.get(i)*1.0/10000);
            item.asks = listTmep;
        }
        if(in.get(FDTFields.AskVolumeArray)!=null) {
            item.askVols = (List<Long>) in.get(FDTFields.AskVolumeArray);
        }

        if(in.get(FDTFields.TradingDay)!=null)
            item.tradeDate =(long)in.get(FDTFields.TradingDay);
        if(in.get(FDTFields.Time)!=null)
            item.timeStamp = (long)in.get(FDTFields.Time);
//        item.totalVolume = (double) in.get(FDTFields.Volume);
        return item;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("symbol:"+symbol);
        sb.append(" tradeDate:"+tradeDate);
        sb.append(" timeStamp:"+timeStamp);
        sb.append(" timeRec:"+timeRec);
        sb.append(" preClose:"+preClose);
        sb.append(" open:"+open);
        sb.append(" high:"+high);
        sb.append(" low:"+low);
        sb.append(" last:"+last);
        sb.append(" totalVolume:"+totalVolume);
        sb.append(" turnover:"+turnover);

        sb.append(" ask:"+ask);
        sb.append(" bid:"+bid);
        sb.append(" asks:"+asks);
        sb.append(" askVols:"+askVols);
        sb.append(" bids:"+bids);
        sb.append(" bidVols:"+bidVols);
        return sb.toString();
    }
}
