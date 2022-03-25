package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.chat.entry.Message;
import com.lingshao.secondhand.chat.entry.MessageList;
import com.lingshao.secondhand.chat.service.MsgService;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Api(tags = "聊天")
@RestController
@RequestMapping("/chat")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @ApiOperation(value = "发送消息")
    @PostMapping("/v1/sendMessage")
    public ResponseModel<Integer> sendMessage(@ApiIgnore() @CurrentUser User user,
                                              @ApiParam(required = true,value = "信息")@RequestBody Message message){
        return ResponseHelper.buildResponseModel(msgService.sendMessage(user.getUserNumber(), message));
    }

    @ApiOperation(value = "接受消息")
    @GetMapping("/v1/getMessage")
    public ResponseModel<CopyOnWriteArrayList<Message>> getMessage(@ApiIgnore() @CurrentUser User user,
           @ApiParam(required = true,value = "接收者id")@RequestParam String listenerId){
        return ResponseHelper.buildResponseModel(msgService.getMessage(user.getUserNumber(),listenerId));
    }

    @ApiOperation(value = "消息列表")
    @PostMapping("/v1/getMessageList")
    public ResponseModel<List<MessageList>> getMessageList(@ApiIgnore() @CurrentUser User user){
        return ResponseHelper.buildResponseModel(msgService.getMessageList(user.getUserNumber()));
    }

}
