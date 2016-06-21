package com.hundsun.internet.billingsystem.service.common.message;

import org.springframework.ui.Model;

import com.hundsun.internet.billingsystem.service.common.message.impl.MessageList;
import com.hundsun.internet.billingsystem.service.common.message.impl.MessageObject;

/**
 * 消息服务类.
 * @author: shangke
 * @since: 2014年3月3日 下午2:06:30
 * @history:
 */
public interface MessageService {
    /**
     * 发送消息到页面.
     * @param mo 消息对象
     * @param model 模型对象
     * @return 消息对象
     */
    MessageObject send2Page(MessageObject mo, Model model);

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @param messageType 消息类型
     * @param model 模型对象
     * @return 消息对象
     */
    MessageObject send2Page(String content, int messageType, Model model);

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @param messageType 消息类型
     * @return 消息对象
     */
    MessageObject send2Page(String content, int messageType);

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    MessageObject sendInfo2Page(String content);

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    MessageObject sendWarn2Page(String content);

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    MessageObject sendError2Page(String content);

    /**
     * 获取本线程的POOLID.如果本线程中有填充则，必定有。反之则没有.一旦获取后，则自动删除线程ID与poolId间的关系.
     * @return 消息池ID
     * @create: 2014年3月4日 下午3:22:39 shangke
     * @history:
     */
    String getMyPoolId();

    /**
     * 设置POOLID，与线程ID关联.
     * @param poolId 与线程ID关联
     * @create: 2014年3月5日 上午8:55:00 shangke
     * @history: 增加 @param。
     */
    void setMyPoolId(String poolId);

    /**
     * 根据poolId获取列表.一旦获取后，自动移除.
     * @param poolId 消息池ID
     * @return 列表
     * @create: 2014年3月4日 下午4:16:14 shangke
     * @history:
     */
    MessageList getMessageList(String poolId);

    /**
     * 获取消息字符串.
     * @return 列表
     * @create: 2014年3月4日 下午4:16:14 shangke
     * @history:
     */
    String getAllMessageString();
}
