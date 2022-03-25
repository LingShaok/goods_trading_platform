package com.lingshao.secondhand.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lingshao.constant.CommonConstant;
import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.commodity.dao.CommodityDao;
import com.lingshao.secondhand.commodity.entry.Commodity;
import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.secondhand.order.dao.OrderDao;
import com.lingshao.secondhand.order.entry.Order;
import com.lingshao.secondhand.order.service.OrderService;
import com.lingshao.secondhand.service.RedisService;
import com.lingshao.util.ComUtil;
import com.lingshao.util.DateUtil;
import com.lingshao.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    RedisService redisService;

    @Override
    public Integer crateOrderInRedis(String stuNumber, Order order) {
        if (stuNumber.equals(order.getSellerId())) {
            throw new BusinessException(ErrCode.ACCESS_LIMITED, PublicResultConstant.BUYER_SELLER_ERROR);
        }
        order.setBuyerId(stuNumber);
        if (ComUtil.isEmpty(order.getGoodsId())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.GOODS_ID_EMPTY);
        }
        if (ComUtil.isEmpty(order.getSellerId())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.SELLER_ID_EMPTY);
        }
        if (ComUtil.isEmpty(order.getBuyerId())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.BUYER_ID_EMPTY);
        }
        if (ComUtil.isEmpty(order.getTransaction())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.TRANSACTION_IS_EMPTY);
        }
        if (ComUtil.isEmpty(order.getNumber())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.NUMBER_IS_EMPTY);
        }
        String orderNumber = String.valueOf(OrderUtil.SnowFlake.nextId());
        order.setOrderNumber(orderNumber);
        order.setCreateTime(DateUtil.getNowTime());
        Commodity commodity = commodityDao.getDetailedCommodity(order.getGoodsId());
        if (Integer.valueOf(commodity.getNumber())<Integer.valueOf(order.getNumber())) {
            throw new BusinessException(ErrCode.LONG_TIME_NO_WORK, PublicResultConstant.NUMBER_IS_ERROR);
        }
        Integer resultNumber = Integer.valueOf(commodity.getNumber()) - Integer.valueOf(order.getNumber());
        commodityDao.updateCommodityNumber(order.getGoodsId(), resultNumber);
        redisService.set(String.valueOf(orderNumber), JSON.toJSONString(order));
        //订单过期时间2小时
        redisService.dataExpire(String.valueOf(orderNumber), 60 * 60 * 2);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public List<Order> getMyOrder(String stuNumber) {
        //从redis中获取
        Set<String> keysAllSet = redisService.getKeysAll();
        List<Order> ordersList = new ArrayList<>(8);
        if (!ComUtil.isEmpty(keysAllSet)) {
            Map<String, String> getValues = redisService.getAllValue(keysAllSet);
            for (String key : getValues.keySet()) {
                Order order = JSONObject.parseObject(getValues.get(key), Order.class);
                order.setIsPayBuyer("0");
                order.setIsPaySeller("0");
                Commodity commodity = commodityDao.getDetailedCommodity(order.getGoodsId());
                order.setGoodsName(commodity.getCommodityName());
                ordersList.add(order);
            }
        }
        //从数据库中获取
        List<Order> resultOrder = orderDao.getMyOrder(stuNumber);
        //去重取并集
//        ordersList = ordersList.stream().filter(item -> item.getBuyerId().equals(stuNumber)).collect(Collectors.toList());
        System.out.println("---quchong--"+ordersList);
        resultOrder.addAll(ordersList);
        resultOrder = resultOrder.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < resultOrder.size(); i++) {
            System.out.println("-----"+resultOrder.get(i).getOrderNumber());
        }
        return resultOrder;
    }

    @Override
    public Integer delMyOrder(String stuNumber, String orderNumber) {
//        //从redis中获取
//        Set<String> keysAllSet = redisService.getKeysAll();
//        List<Order> ordersRedisList = new ArrayList<>(8);
//        if (!ComUtil.isEmpty(keysAllSet)) {
//            Map<String, String> getValues = redisService.getAllValue(keysAllSet);
//            for (String key : getValues.keySet()) {
//                Order order = JSONObject.parseObject(getValues.get(key), Order.class);
//                ordersRedisList.add(order);
//            }
//        }
//        List<Order> redisOrder = ordersRedisList.stream()
//                .filter(e -> e.getOrderNumber().equals(orderNumber)).collect(Collectors.toList());
//        if (!ComUtil.isEmpty(redisOrder)){
//            if (ComUtil.isEmpty(redisOrder.get(0).getIsPaySeller())){
//                throw new BusinessException(ErrCode.SELLER_NOT_SURE, PublicResultConstant.SELLER_NOT_SURE);
//            }
//            if (ComUtil.isEmpty(redisOrder.get(0).getIsPayBuyer())){
//                throw new BusinessException(ErrCode.BUYER_NOT_SURE, PublicResultConstant.BUYER_NOT_SURE);
//            }
//        }
        //从数据库读取数据
        Order dataOrder = orderDao.getOrderByOrderNum(orderNumber);
        if (dataOrder.getIsPaySeller().equals("0")) {
            throw new BusinessException(ErrCode.SELLER_NOT_SURE, PublicResultConstant.SELLER_NOT_SURE);
        }
        if (dataOrder.getIsPayBuyer().equals("0")) {
            throw new BusinessException(ErrCode.BUYER_NOT_SURE, PublicResultConstant.BUYER_NOT_SURE);
        }
//        //从redis中删除
//        redisService.remove(orderNumber);
        //从数据库中删除
        System.out.println("go delete");
        orderDao.delMyOrder(stuNumber, orderNumber);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public Integer sureOrder(String stuNumber, String orderNumber) {
        //从redis中获取
        Order redisOrder = JSONObject.parseObject(redisService.get(orderNumber),Order.class);
        System.out.println(redisOrder);
        if(!ComUtil.isEmpty(redisOrder)){
            if (redisOrder.getBuyerId().equals(stuNumber)){
                System.out.println("r买家确认");
                redisOrder.setIsPayBuyer("1");
            }
            if (redisOrder.getSellerId().equals(stuNumber)){
                System.out.println("r卖家确认");
                redisOrder.setIsPaySeller("1");
            }
            orderDao.addOneOrder(redisOrder);
            redisService.remove(orderNumber);
        }else {

            Order order = orderDao.getOrderByOrderNum(orderNumber);
            if (order.getSellerId().equals(stuNumber)){
                orderDao.sureSellerOrder(orderNumber);
                System.out.println("卖家确认");
            }
            if (order.getBuyerId().equals(stuNumber)){
                orderDao.sureBuyerOrder(orderNumber);
                System.out.println("买家确认");
            }
        }
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public Integer cancelOrder(String orderNumber) {
        Order redisOrder = JSONObject.parseObject(redisService.get(orderNumber),Order.class);
        if (ComUtil.isEmpty(redisOrder)){
            return CommonConstant.MESSAGE_CODE.FAILED;
        }else {
            Commodity commodity = commodityDao.getDetailedCommodity(redisOrder.getGoodsId());
            Integer resultNumber = Integer.valueOf(commodity.getNumber()) + Integer.valueOf(redisOrder.getNumber());
            commodityDao.updateCommodityNumber(redisOrder.getGoodsId(), resultNumber);
            redisService.remove(orderNumber);
            return CommonConstant.MESSAGE_CODE.SUCCESS;
        }
    }

}
