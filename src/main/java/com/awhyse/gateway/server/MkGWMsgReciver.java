package com.awhyse.gateway.server;

/**
 * Created by whyse
 * on 2017/8/7 10:47
 */
public interface MkGWMsgReciver {
    /**
     * 上游返回的信息
     *  注意，这边是同步回调，不能长时间阻塞这个回调接口。否则超时，客户端重连
     * @param msg
     */
    void onMsg(Object msg);
}
