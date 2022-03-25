package com.lingshao.secondhand.controller;

import com.lingshao.annotation.CurrentUser;
import com.lingshao.secondhand.config.ResponseHelper;
import com.lingshao.secondhand.config.ResponseModel;
import com.lingshao.secondhand.order.entry.Order;
import com.lingshao.secondhand.order.service.OrderService;
import com.lingshao.secondhand.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "订单", description = "订单")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "生成订单")
    @GetMapping("/v1/createOrder")
    public ResponseModel<Integer> createOrder(
            @ApiIgnore() @CurrentUser User user,
            @ApiParam(required = false, value = "订单") Order order) {
        return ResponseHelper.buildResponseModel(orderService.crateOrderInRedis(user.getUserNumber(),order));
    }

    @ApiOperation(value = "获取我的订单")
    @GetMapping("/v1/getMyOrder")
    public ResponseModel<List<Order>> getMyOrder(
            @ApiIgnore() @CurrentUser User user) {

        List<Order> resultOrder = orderService.getMyOrder(user.getUserNumber());
        return ResponseHelper.buildResponseModel(orderService.getMyOrder(user.getUserNumber()));
    }

    @ApiOperation(value = "删除订单")
    @GetMapping("/v1/delOrder")
    public ResponseModel<Integer> delOrder(
            @ApiIgnore() @CurrentUser User user,
            @ApiParam(required = true, value = "订单编号") @RequestParam String orderNumber) {
        return ResponseHelper.buildResponseModel(orderService.delMyOrder(user.getUserNumber(),orderNumber));
    }

    @ApiOperation(value = "确认订单")
    @GetMapping("/v1/sureOrder")
    public ResponseModel<Integer> sureOrder(
            @ApiIgnore() @CurrentUser User user,
            @ApiParam(required = true, value = "订单编号") @RequestParam String orderNumber) {
        return ResponseHelper.buildResponseModel(orderService.sureOrder(user.getUserNumber(),orderNumber));
    }

    @ApiOperation(value = "取消订单")
    @GetMapping("/v1/cancelOrder")
    public ResponseModel<Integer> cancelOrder(
            @ApiParam(required = true, value = "订单编号") @RequestParam String orderNumber) {
        return ResponseHelper.buildResponseModel(orderService.cancelOrder(orderNumber));
    }
}
