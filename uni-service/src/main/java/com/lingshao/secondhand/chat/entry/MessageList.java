package com.lingshao.secondhand.chat.entry;

import lombok.Data;

@Data
public class MessageList {
    private String listenerName;
    private String listenerId;
    private String speakerName;
    private String speakerId;
    private String lastMassage;
    private String lastTime;
}
