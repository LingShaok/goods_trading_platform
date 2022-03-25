package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.commodity.entry.Commodity;
import com.lingshao.secondhand.commodity.service.CommodityService;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

/**
 * @description 商品控制层
 * @author LingShao
 * @date 2021/4/27
 */
@Api(tags = "商品", description = "商品")
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiOperation(value = "获取商品")
    @GetMapping("/v1/getCommodity")
    public ResponseModel<List<Commodity>> getCommodity(
            @ApiParam(required = false, value = "搜索条件") @RequestParam(required = false) String conditionStr) {
        return ResponseHelper.buildResponseModel(commodityService.getCommodity(conditionStr));
    }

    @ApiOperation(value = "获取单个商品")
    @GetMapping("/v1/getOneCommodity")
    public ResponseModel<Commodity> getOneCommodity(@ApiIgnore() @CurrentUser User user,
            @ApiParam(required = true, value = "商品id") @RequestParam String commodityId) {
        return ResponseHelper.buildResponseModel(commodityService.getOneCommodity(commodityId));
    }

    @ApiOperation(value = "上传or修改商品，有商品id-修改，无-新增")
    @PostMapping(value = "/v1/addOrUpdateCommodity")
    public ResponseModel<Integer> addOrUpdateCommodity(@ApiIgnore() @CurrentUser User user,
           @ApiParam(required = true,value = "商品信息")@RequestBody Commodity commodity) throws IOException {
        return ResponseHelper.buildResponseModel(commodityService.addOrUpdateCommodity(user.getUserNumber(),commodity));
    }

    @ApiOperation(value = "获取我上传的商品")
    @GetMapping("/v1/getMyCommodity")
    public ResponseModel<List<Commodity>> getMyCommodity(@ApiIgnore() @CurrentUser User user){
        return ResponseHelper.buildResponseModel(commodityService.getMyCommodity(user.getUserNumber()));
    }

    @ApiOperation(value = "删除我的商品")
    @GetMapping("/v1/deleteMyCommodity")
    public ResponseModel<Integer> deleteMyCommodity(@ApiIgnore() @CurrentUser User user,
           @ApiParam(required = true, value = "商品id") @RequestParam String commodityId                                                 ){
        return ResponseHelper.buildResponseModel(commodityService.deleteMyCommodity(user.getUserNumber(),commodityId));
    }

    @ApiOperation(value = "批量删除我的商品")
    @GetMapping("/v1/deleteLotMyCommodity")
    public ResponseModel<Integer> deleteLotMyCommodity(@ApiIgnore() @CurrentUser User user,
           @ApiParam(required = true, value = "JSON类型商品id集合") @RequestBody String commodityId                                                 ){
        return ResponseHelper.buildResponseModel(commodityService.deleteLotMyCommodity(user.getUserNumber(),commodityId));
    }
}
