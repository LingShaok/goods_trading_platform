package com.lingshao.secondhand.chat.dao;

import com.lingshao.secondhand.chat.entry.Message;
import com.lingshao.secondhand.chat.entry.MessageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public interface ChatDao {

    Integer addChat(@Param("Message") Message message);

    CopyOnWriteArrayList<Message> getMessage(@Param("speakerId") String speakerId, @Param("listenerId") String listenerId);

    List<MessageList> getMessageList(@Param("speakerId") String speakerId);

    List<MessageList> getLisMessageList(@Param("listenerId")String listenerId);

    List<MessageList> getSpeMessageList(@Param("speakerId") String speakerId);
}
