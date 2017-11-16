package com.awhyse.gateway.server;

import com.awhyse.gateway.common.Quote;

/**
 * 该回调存在同步情况，不要在回调接口里面做耗时的同步操作
 * Created by whyse
 * on 2017/8/7 10:47
 */
public interface MkGWMsgReciver {
    /**
     * 连上主机后回调，这里可以请求登录了
     * 如果不需要登录，则这里可以订阅等操作
     */
    void onActive();
    /**
     *  state :  1:登录成功 ； 0:登录中 -1:用户名密码错误  -2:其他用户占用
     * @param state
     * @param rspMsg
     */
    void rspLogin(int state, String rspMsg);
    /**
     * 订阅的symbol行情从这边给出,请入队列处理，不要阻塞回调
     * @param quote
     */
    void onQupte(Quote quote);
    /**
     * 上游返回的信息
     *  注意，这边是同步回调，不能长时间阻塞这个回调接口。否则超时，客户端重连
     * @param msg
     */
    void onMsg(Object msg);

}
