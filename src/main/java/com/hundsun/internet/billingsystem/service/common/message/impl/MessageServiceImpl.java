package com.hundsun.internet.billingsystem.service.common.message.impl;

import java.util.Hashtable;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hundsun.internet.billingsystem.common.Constant;
import com.hundsun.internet.billingsystem.service.common.message.MessageService;

/**
 * 消息服务实现类.
 * @author: shangke
 * @since: 2014年3月3日 下午2:39:33
 * @history:
 */
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * @Fields messagePool : 消息对象池
     */
    private static Hashtable<String, MessageList> messagePool = new Hashtable();

    /**
     * @Fields threadMessageHT : 线程ID与消息池ID对应表
     */
    private static Hashtable<Long, String> threadMessageHT = new Hashtable();

    /**
     * 发送消息到页面.
     * @param mo 消息对象
     * @param model 模型对象
     * @return 消息对象
     */
    public MessageObject send2Page(MessageObject mo, Model model) {
        MessageList ml = null;

        if (ml == null && mo.getMessagePoolId() != null) {
            ml = messagePool.get(mo.getMessagePoolId());
        }

        if (ml == null
                && threadMessageHT.get(Thread.currentThread().getId()) != null) {
            ml = messagePool.get(threadMessageHT.get(
                Thread.currentThread().getId()).toString());
        }

        if (ml == null && model != null
                && model.containsAttribute(Constant.POOL_ID_KEY_IN_MODEL)) {
            ml = (MessageList) messagePool.get(model.asMap().get(
                Constant.POOL_ID_KEY_IN_MODEL));
        }

        if (ml == null) {
            ml = new MessageList();
            ml.setPoolId(java.util.UUID.randomUUID().toString());
            messagePool.put(ml.getPoolId(), ml);
        }

        ml.add(mo);
        mo.setMessagePoolId(ml.getPoolId());
        threadMessageHT.put(Thread.currentThread().getId(), ml.getPoolId());
        return mo;
    }

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @param messageType 消息类型
     * @param model 模型对象
     * @return 消息对象
     */
    public MessageObject send2Page(String content, int messageType, Model model) {
        MessageObject mo = new MessageObject();
        mo.setContent(content);
        mo.setMessageType(messageType);
        mo.setMessageId(java.util.UUID.randomUUID().toString());
        return this.send2Page(mo, model);
    }

    /**
     * 自动清理那些垃圾数据.
     * @create: 2014年3月4日 下午2:28:45 shangke
     * @history:
     */
    private void autoClean() {
        // TODO .........
    }

    /**
     * 获取本线程的POOLID.如果本线程中有填充则，必定有。反之则没有.一旦获取后，则自动删除线程ID与poolId间的关系.
     * @return 消息池ID
     * @create: 2014年3月4日 下午3:22:39 shangke
     * @history:
     */
    public String getMyPoolId() {
        String poolId = threadMessageHT.get(Thread.currentThread().getId());
        threadMessageHT.remove(Thread.currentThread().getId());
        return poolId;
    }

    /**
     * 根据poolId获取列表.一旦获取后，自动移除.
     * @param poolId 消息池ID
     * @return 列表
     * @create: 2014年3月4日 下午4:16:14 shangke
     * @history:
     */
    public MessageList getMessageList(String poolId) {
        MessageList ml = this.messagePool.get(poolId);
        this.messagePool.remove(poolId);
        return ml;
    }

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @param messageType 消息类型
     * @return 消息对象
     */
    public MessageObject send2Page(String content, int messageType) {
        return this.send2Page(content, messageType, null);
    }

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    public MessageObject sendInfo2Page(String content) {
        return this.send2Page(content, MessageObject.MESSAGE_TYPE_INFO);
    }

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    public MessageObject sendWarn2Page(String content) {
        return this.send2Page(content, MessageObject.MESSAGE_TYPE_WARNING);
    }

    /**
     * 发送消息到页面.
     * @param content 消息内容
     * @return 消息对象
     */
    public MessageObject sendError2Page(String content) {
        return this.send2Page(content, MessageObject.MESSAGE_TYPE_ERROR);
    }

    /**
     * 设置POOLID，与线程ID关联.
     * @create: 2014年3月5日 上午8:55:00 shangke
     * @history:
     */
    public void setMyPoolId(String poolId) {
        this.threadMessageHT.put(Thread.currentThread().getId(), poolId);

    }

    /**
     * 获取消息字符串.
     * @return 列表
     * @create: 2014年3月4日 下午4:16:14 shangke
     * @history:
     */
    public String getAllMessageString() {
        String poolId = this.getMyPoolId();
        if (poolId == null) {
            return "";
        }
        MessageList list = this.getMessageList(poolId);
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getContent() + "\r\n");
        }
        return sb.toString();
    }
}
