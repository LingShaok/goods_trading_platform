package com.lingshao.secondhand.chat.service;

import com.lingshao.secondhand.chat.entry.Message;
import com.lingshao.secondhand.chat.entry.MessageList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MsgService {
    /**
     * @description 发送消息
     * @author LingShao
     * @date 2021/5/10
     * @param @param null:
     * @return
     */
    Integer sendMessage(String userNumber,Message message);

    /**
     * @description 获取消息
     * @author LingShao
     * @date 2021/5/10
     * @return
     */
    CopyOnWriteArrayList<Message> getMessage(String userNumber, String sellerNumber);

    /**
     * @description 消息列表
     * @author LingShao
     * @date 2021/5/16
     * @param @param null:
     * @return
     */
    List<MessageList> getMessageList(String userNumber);
}
