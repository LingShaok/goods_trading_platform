package com.lingshao.secondhand.chat.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Message {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "消息发送者")
    private String speakerId;
    @ApiModelProperty(value = "接收者")
    private String listenerId;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "创建者")
    private String createTime;
    @ApiModelProperty(value = "是不是我")
    private String isMe;
}
