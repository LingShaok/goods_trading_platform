package com.lingshao.secondhand.controller;

import com.lingshao.secondhand.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.secondhand.user.service.LoginService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @description
 * @author LingShao
 * @date 2021/4/18
 */
@Api(tags = "用户",description = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @ApiOperation(value = "个人中心")
    @GetMapping("/v1/getPersonalCenter")
    public ResponseModel<User> getPersonalCenter(@ApiIgnore() @CurrentUser User user) throws Exception {
        return ResponseHelper.buildResponseModel(loginService.getPersonalCenter(user));
    }

    @ApiOperation(value = "修改密码")
    @GetMapping("/v1/updateUserPwd")
    public ResponseModel<Integer> updateUserPwd(@ApiIgnore() @CurrentUser User user,
                     @ApiParam(required = true,value = "旧密码")@RequestParam(required = true)String oldPwd,
                     @ApiParam(required = true,value = "新密码")@RequestParam(required = true)String newPwd) throws Exception {
        return ResponseHelper.buildResponseModel(loginService.updateUserPwd(user.getUserNumber(),oldPwd,newPwd));
    }
    @ApiOperation(value = "修改个人信息")
    @PostMapping("/v1/updateStuInfo")
    public ResponseModel<Integer> updateStuInfo(@ApiIgnore() @CurrentUser User user,
                     @ApiParam(required = true,value = "个人信息")@RequestBody String updateUser){
        return ResponseHelper.buildResponseModel(userService.updateUserInfo(user.getUserNumber(),updateUser));
    }

}
