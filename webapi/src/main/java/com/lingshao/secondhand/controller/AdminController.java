package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.annotation.Pass;
import com.lingshao.secondhand.commodity.entry.Commodity;
import com.lingshao.secondhand.commodity.service.CommodityService;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.secondhand.user.service.LoginService;
import com.lingshao.secondhand.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author LingShao
 * @description 管理员
 * @date 2021/5/11
 */

@Api(tags = "管理员", description = "管理员功能")
@RestController
@RequestMapping("/adminLogin")
public class AdminController {

    @Resource
    private LoginService loginService;

    @Resource
    private CommodityService commodityService;

    @Resource
    private UserService userService;

    /**
     * @param number   学号
     * @param password 密码
     * @return
     * @description 管理员登录
     * @author LingShao
     * @date 2021/4/17
     */
    @ApiOperation(value = "登录")
    @GetMapping("/v1/adminLogin")
    @Pass
    public ResponseModel<Map<String,Object>> adminLogin2(
            @ApiParam(value = "管理员账号", name = "number") @RequestParam String number,
            @ApiParam(value = "登录密码", name = "password") @RequestParam String password) throws Exception {
        return ResponseHelper.buildResponseModel(loginService.adminLogin(number, password));
    }

    @ApiOperation(value = "获取商品")
    @PostMapping("/v1/getCommodity")
    public ResponseModel<List<Commodity>> getCommodity(@ApiIgnore() @CurrentUser User user,
            @ApiParam(required = false, value = "搜索条件") @RequestParam(required = false) String conditionStr) {
        return ResponseHelper.buildResponseModel(commodityService.getCommodity(conditionStr));
    }

    @ApiOperation(value = "获取单个商品")
    @GetMapping("/v1/getOneCommodity")
    public ResponseModel<Commodity> getOneCommodity(
           @ApiParam(required = true, value = "商品id") @RequestParam String commodityId) {
        return ResponseHelper.buildResponseModel(commodityService.getOneCommodity(commodityId));
    }

    @ApiOperation(value = "删除商品")
    @GetMapping("/v1/adminDelCommodity")
    public ResponseModel<Integer> adminDelCommodity(
            @ApiParam(value = "商品id", name = "commodityId") @RequestParam String commodityId) {
        return ResponseHelper.buildResponseModel(commodityService.adminDeleteCommodity(commodityId));
    }

    @ApiOperation(value = "查看所有用户")
    @GetMapping("/v1/getAllUser")
    public ResponseModel<List<User>> getUser(){
        return ResponseHelper.buildResponseModel(userService.selectAllUser());
    }

    @ApiOperation(value = "搜索单个用户")
    @GetMapping("/v1/getOneUser")
    public ResponseModel<User> getOntUser(
            @ApiParam(value = "学生学号", name = "stuNumber") @RequestParam String stuNumber){
        return ResponseHelper.buildResponseModel(userService.getOneUser(stuNumber));
    }

    @ApiOperation(value = "禁用/启用用户")
    @GetMapping("/v1/isValidUser")
    public ResponseModel<Integer> isValidUser(
            @ApiParam(value = "学生学号", name = "stuNumber") @RequestParam String stuNumber,
            @ApiParam(value = "启用0；禁用1", name = "isValid") @RequestParam String isValid){
        return ResponseHelper.buildResponseModel(userService.isValidUser(stuNumber,isValid));
    }
}
