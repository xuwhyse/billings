package com.hundsun.internet.billingsystem.service.common.message.impl;

/**
 * 消息对象.
 * @author: shangke
 * @since: 2014年3月3日 下午2:36:53
 * @history:
 */
public class MessageObject {
    /**
     * @Fields MESSAGE_TYPE_INFO : 消息类型：信息
     */
    public static final int MESSAGE_TYPE_INFO = 0;

    /**
     * @Fields MESSAGE_TYPE_WARNING : 消息类型：警告
     */
    public static final int MESSAGE_TYPE_WARNING = 1;

    /**
     * @Fields MESSAGE_TYPE_ERROR : 消息类型：异常
     */
    public static final int MESSAGE_TYPE_ERROR = 2;

    /**
     * @Fields content : 消息内容
     */
    private String content;

    /**
     * @Fields messageType : 消息类型
     */
    private int messageType;

    /**
     * @Fields messageId : 消息ID
     */
    private String messageId;

    /**
     * @Fields messagePoolId : 消息池ID
     */
    private String messagePoolId;

    /**
     * 获取消息ID.
     * @return 消息ID
     * @create: 2014年3月3日 下午2:45:33 shangke
     * @history:
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 设置消息ID.
     * @param messageId 消息ID
     * @create: 2014年3月3日 下午2:46:01 shangke
     * @history:
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * 获取消息池ID.
     * @return 消息池ID
     * @create: 2014年3月3日 下午2:47:25 shangke
     * @history:
     */
    public String getMessagePoolId() {
        return messagePoolId;
    }

    /**
     * 设置消息池ID.
     * @param messagePoolId 消息池ID
     * @create: 2014年3月3日 下午2:47:55 shangke
     * @history:
     */
    public void setMessagePoolId(String messagePoolId) {
        this.messagePoolId = messagePoolId;
    }

    /**
     * 获取消息内容.
     * @return 消息内容
     * @create: 2014年3月3日 下午2:37:53 shangke
     * @history:
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容.
     * @param content 消息内容
     * @create: 2014年3月3日 下午2:38:05 shangke
     * @history:
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取消息类型.
     * @return 消息类型
     * @create: 2014年3月3日 下午2:38:49 shangke
     * @history:
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型.
     * @param messageType 消息类型
     * @create: 2014年3月3日 下午2:39:05 shangke
     * @history:
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
