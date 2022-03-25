package com.lingshao.secondhand.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.lingshao.annotation.Pass;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.user.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 登录
 * @author LingShao
 * @date 2021/3/22 10:09.
 */
@Api(tags = "登录",description = "登录")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    /**
     * @description 登录
     * @author LingShao
     * @date 2021/4/17
     * @param number 学号
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "登录")
    @GetMapping("/v1/adminLogin")
    @Pass
    public ResponseModel<Map<String,Object>> adminLogin(
            @ApiParam(value = "学号", name = "number") @RequestParam String number,
            @ApiParam(value = "登录密码", name = "password") @RequestParam String password) throws Exception {
        return ResponseHelper.buildResponseModel(loginService.userLogin(number,password));
    }

}
