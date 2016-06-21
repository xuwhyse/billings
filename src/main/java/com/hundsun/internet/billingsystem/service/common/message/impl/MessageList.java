package com.hundsun.internet.billingsystem.service.common.message.impl;

import java.util.Calendar;

/**
 * 消息对象列表.
 * @author: shangke
 * @since: 2014年3月4日 下午2:41:15
 * @history:
 */
public class MessageList extends java.util.Vector {
    /**
     * @Fields lastModifyTime : 最后修改时间
     */
    private Calendar lastModifyTime;

    /**
     * @Fields poolId : 消息池ID
     */
    private String poolId;

    /**
     * 获取消息池ID.
     * @return 消息池ID
     * @create: 2014年3月4日 下午3:15:17 shangke
     * @history:
     */
    public String getPoolId() {
        return poolId;
    }

    /**
     * 设置消息池ID.
     * @param poolId 消息池ID
     * @create: 2014年3月4日 下午3:15:47 shangke
     * @history:
     */
    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    /**
     * 获取最后修改时间.
     * @return 最后修改时间
     * @create: 2014年3月4日 下午2:46:06 shangke
     * @history:
     */
    public Calendar getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * 设置最后修改时间.
     * @param lastModifyTime 最后修改时间
     * @create: 2014年3月4日 下午2:48:33 shangke
     * @history:
     */
    public void setLastModifyTime(Calendar lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public boolean add(Object object) {
        this.lastModifyTime = Calendar.getInstance();
        super.add(object);
        return true;
    }

    /**
     * 获取消息对象.
     * @param index 索引
     * @return 消息对象
     * @see java.util.Vector#get(int)
     */
    public MessageObject get(int index) {
        return (MessageObject) super.get(index);
    }
}
