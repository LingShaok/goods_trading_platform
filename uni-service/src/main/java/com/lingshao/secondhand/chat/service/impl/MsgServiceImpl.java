package com.lingshao.secondhand.chat.service.impl;

import com.lingshao.secondhand.chat.dao.ChatDao;
import com.lingshao.secondhand.chat.entry.Message;
import com.lingshao.secondhand.chat.entry.MessageList;
import com.lingshao.secondhand.chat.service.MsgService;
import com.lingshao.secondhand.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    private ChatDao chatDao;

    @Resource
    private UserDao userDao;

    @Override
    public Integer sendMessage(String userNumber, Message message) {
        message.setSpeakerId(userNumber);
        return chatDao.addChat(message);
    }

    @Override
    public CopyOnWriteArrayList<Message> getMessage(String userNumber, String sellerNumber) {
        CopyOnWriteArrayList<Message> messages = chatDao.getMessage(userNumber, sellerNumber);
        Iterator iterator = messages.iterator();
        while (iterator.hasNext()) {
            Message message = (Message) iterator.next();
            if (message.getSpeakerId().equals(userNumber)) {
                message.setIsMe("1");
            } else {
                message.setIsMe("0");
            }
        }
        return messages;
    }

    @Override
    public List<MessageList> getMessageList(String userNumber) {
        String name = userDao.getUserInfoById(userNumber).getStudentName();
        System.out.println(name+"----");
        List<MessageList> lisMessageList = chatDao.getLisMessageList(userNumber);
        List<MessageList> speMessageList = chatDao.getSpeMessageList(userNumber);
        System.out.println(speMessageList);
        System.out.println("====="+lisMessageList);
        for (int i = 0; i < lisMessageList.size(); i++) {
            lisMessageList.get(i).setListenerName(name);
        }
        for (int i = 0; i < speMessageList.size(); i++) {
            speMessageList.get(i).setSpeakerName(name);
        }
        System.out.println(speMessageList);
        System.out.println("====="+lisMessageList);
        List<MessageList> resultMessageList = new ArrayList<>(8);
        int flag = 0;
        int flag2 = 0;
        if (lisMessageList.size()>speMessageList.size()){
            Iterator iterator = lisMessageList.iterator();
            while (iterator.hasNext()) {
                MessageList messageList = (MessageList) iterator.next();
                for (int i = 0; i < speMessageList.size(); i++) {
                    if (messageList.getSpeakerId().equals(speMessageList.get(i).getListenerId())) {
                        if (messageList.getLastTime().compareTo(speMessageList.get(i).getLastTime()) > 0) {
                            System.out.println("1111");
                            resultMessageList.add(messageList);
                        } else {
                            System.out.println("2222");
                            resultMessageList.add(speMessageList.get(i));
                        }
                        flag++;
                    }
                }
                if (flag==flag2){
                    System.out.println(messageList);
                    resultMessageList.add(messageList);
                }else {
                    flag2 = flag;
                }
            }
        }else {
            Iterator iterator2 = speMessageList.iterator();
            while (iterator2.hasNext()) {
                MessageList messageList = (MessageList) iterator2.next();
                for (int i = 0; i < lisMessageList.size(); i++) {
                    if (messageList.getListenerId().equals(lisMessageList.get(i).getSpeakerId())) {
                        if (messageList.getLastTime().compareTo(lisMessageList.get(i).getLastTime()) > 0) {
                            System.out.println("1111");
                            resultMessageList.add(messageList);
                        } else {
                            System.out.println("2222");
                            resultMessageList.add(lisMessageList.get(i));
                        }
                        flag++;
                    }
                }
                if (flag==flag2){
                    System.out.println(messageList);
                    resultMessageList.add(messageList);
                }else {
                    flag2 = flag;
                }
            }
        }
        return resultMessageList;
    }
}
