package com.lingshao.secondhand.order.dao;

import com.lingshao.secondhand.order.entry.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    /**
     * @description 添加订单
     * @author LingShao
     * @date 2021/5/3
     * @param Order 订单信息
     */
    Integer addOrder(@Param("orderList") List<Order> Order);

    Integer addOneOrder(@Param("order")Order order);
    /**
     * @description 获取我的所有订单
     * @author LingShao
     * @date 2021/5/3
     * @param @param null:
     * @return
     */
    List<Order> getMyOrder(@Param("stuNumber") String stuNumber);

    /**
     * @description 删除订单
     * @author LingShao
     * @date 2021/5/3
     * @param orderNumber 订单号
     * @return
     */
    Integer delMyOrder(@Param("stuNumber") String stuNumber,@Param("orderNumber") String orderNumber);

    /**
     * @description 通过货物id查询订单
     * @author LingShao
     * @date 2021/5/3
     * @param commodityId
     */
    List<Order> getOrderByGoodsId(@Param("commodityId") String commodityId);

    /**
     * @description 通过订单号获取订单信息
     * @author LingShao
     * @date 2021/5/7
     */
    Order getOrderByOrderNum(@Param("orderNumber") String orderNumber);

    /**
     * @description buyer确认订单
     * @author LingShao
     * @date 2021/5/7
     */
    Integer sureBuyerOrder(@Param("orderNumber")String orderNumber);

    /**
     * @description seller确认订单
     * @author LingShao
     * @date 2021/5/7
     */
    Integer sureSellerOrder(@Param("orderNumber")String orderNumber);
}
