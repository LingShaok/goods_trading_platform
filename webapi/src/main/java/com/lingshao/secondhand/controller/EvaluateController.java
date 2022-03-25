package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.evaluate.entity.Evaluate;
import com.lingshao.secondhand.evaluate.service.EvaluateService;
import com.lingshao.secondhand.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * @description 评论控制层
 * @author LingShao
 * @date 2021/5/10
 */

@Api(tags = "评论", description = "评论")
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @ApiOperation(value = "新增评论")
    @PostMapping("/v1/addEvaluate")
    public ResponseModel<Integer> getCommodity(@ApiIgnore() @CurrentUser User user,
             @RequestBody(required = true) Evaluate evaluate) {
        return ResponseHelper.buildResponseModel(evaluateService.addEvaluate(user.getUserNumber(),evaluate));
    }

    @ApiOperation(value = "获取我的评论(我评论别人的)")
    @PostMapping("/v1/getMyEvaluate")
    public ResponseModel<List<Evaluate>> getMyEvaluate(@ApiIgnore() @CurrentUser User user) {
        return ResponseHelper.buildResponseModel(evaluateService.getEvaluateByBuyerId(user.getUserNumber()));
    }

    @ApiOperation(value = "获取我的评论(评论别人评论我的)")
    @PostMapping("/v1/getMyEvaluateOther")
    public ResponseModel<List<Evaluate>> getMyEvaluateOther(@ApiIgnore() @CurrentUser User user) {
        return ResponseHelper.buildResponseModel(evaluateService.getEvaluateBySellerId(user.getUserNumber()));
    }

    @ApiOperation(value = "获取商家评论")
    @PostMapping("/v1/getSellerEvaluate")
    public ResponseModel<List<Evaluate>> getSellerEvaluate(@RequestParam(required = true) String sellerId) {
        return ResponseHelper.buildResponseModel(evaluateService.getEvaluateBySellerId(sellerId));
    }

    @ApiOperation(value = "判断自己是否评论0:未评论,1:已评论")
    @GetMapping("/v1/getIsEvaluate")
    public ResponseModel<Integer> getIsEvaluate(@ApiIgnore() @CurrentUser User user,
                                                @RequestParam(required = true) String orderNumber) {
        return ResponseHelper.buildResponseModel(evaluateService.isEvaluate(user.getUserNumber(),orderNumber));
    }

    @ApiOperation(value = "删除我的评论(我评论别人的)")
    @PostMapping("/v1/delEvaluate")
    public ResponseModel<Integer> delMyEvaluate(@ApiIgnore() @CurrentUser User user,
                                                @RequestParam String evaluateId){
        return ResponseHelper.buildResponseModel(evaluateService.delEvaluate(evaluateId,user.getUserNumber()));
    }
}
