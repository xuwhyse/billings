//package com.awhyse.cache;
//
//import cn.gov.zcy.paas.finance.loan.api.internal.po.gateway.BankApplyLoanPO;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author xumin
// * @date 2019-07-17 16:02
// */
//@Service
//public class CacheTestService {
//
//    //第二级为null
//    @RedisCache(key = "test--{para.channelCode}--aa",expired = 5)
//    public List<BankApplyLoanPO> test1(Boolean update,BankApplyLoanPO para,String str){
//        List<BankApplyLoanPO> list = new ArrayList<>(2);
//        BankApplyLoanPO item = new BankApplyLoanPO();
//        item.setIntentionCode("sdgfh");
//        item.setTime("dgghggg");
//        list.add(item);
//
//        list.add(new BankApplyLoanPO());
//
//        return list;
//
//    }
//
//    //没有参数变量
//    @RedisCache(key = "test-aa",expired = 55,update = "{update}")
//    public List<BankApplyLoanPO> test(int update,BankApplyLoanPO para,String str){
//        List<BankApplyLoanPO> list = new ArrayList<>(2);
//        BankApplyLoanPO item = new BankApplyLoanPO();
//        item.setIntentionCode("sdgfh");
//        item.setTime("dgghggg");
//        list.add(item);
//
//        list.add(new BankApplyLoanPO());
//
//        return list;
//
//    }
//
//    //key写错
//    @RedisCache(key = "test--{update--aa",expired = 5)
//    public List<BankApplyLoanPO> test2(Boolean update,BankApplyLoanPO para,String str){
//        List<BankApplyLoanPO> list = new ArrayList<>(2);
//        BankApplyLoanPO item = new BankApplyLoanPO();
//        item.setIntentionCode("sdgfh");
//        item.setTime("dgghggg");
//        list.add(item);
//
//        list.add(new BankApplyLoanPO());
//
//        return list;
//
//    }
//
//    //二级取值
//    @RedisCache(key = "test--{para.intentionCode}--aa",expired = 5)
//    public List<BankApplyLoanPO> test3(Boolean update,BankApplyLoanPO para,String str){
//        List<BankApplyLoanPO> list = new ArrayList<>(2);
//        BankApplyLoanPO item = new BankApplyLoanPO();
//        item.setIntentionCode("sdgfh");
//        item.setTime("dgghggg");
//        list.add(item);
//
//        list.add(new BankApplyLoanPO());
//
//        return list;
//
//    }
//
//}
