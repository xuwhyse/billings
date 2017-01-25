package com.awhyse.concurrent.netty.decandenc.protocGoogle;

/**
 * 这个结构体很重要，就是传输协议制定,懒序列化。其他地方也能用
 * Created by whyse
 * on 2017/1/25 10:48
 */
public class ProtoBufTransfer {
    /**
     * 协议id
     * @author whyse
     */
    public int protoId;
    /**
     * googleProtoBuf的协议体
     */
    public byte[] protoBytes;
}
