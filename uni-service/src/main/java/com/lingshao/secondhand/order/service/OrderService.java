package com.lingshao.secondhand.order.service;

import com.lingshao.secondhand.order.entry.Order;

import java.util.List;

public interface OrderService {
    /**
     * @description 生成订单存放至redis
     * @author LingShao
     * @date 2021/5/2
     * @param stuNumber
     * @param order 商品
     */
    Integer crateOrderInRedis(String stuNumber, Order order);

    /**
     * @description 获取我的所有订单
     * @author LingShao
     * @date 2021/5/3
     * @param stuNumber
     * @return
     */
    List<Order> getMyOrder(String stuNumber);

    /**
     * @description 删除订单
     * @author LingShao
     * @date 2021/5/3
     * @param orderNumber
     */
    Integer delMyOrder(String stuNumber,String orderNumber);

    /**
     * @description 确认订单
     * @author LingShao
     * @date 2021/5/3
     * @param orderNumber
     */
    Integer sureOrder(String stuNumber,String orderNumber);

    /**
     * @description 取消订单
     * @author LingShao
     * @date 2021/5/11
     * @param orderNumber
     */
    Integer cancelOrder(String orderNumber);
}
