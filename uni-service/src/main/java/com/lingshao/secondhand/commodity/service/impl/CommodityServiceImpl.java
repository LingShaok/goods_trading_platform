package com.lingshao.secondhand.commodity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lingshao.constant.CommonConstant;
import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.commodity.dao.CommodityDao;
import com.lingshao.secondhand.commodity.entry.Commodity;
import com.lingshao.secondhand.commodity.service.CommodityService;
import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.secondhand.order.dao.OrderDao;
import com.lingshao.secondhand.order.entry.Order;
import com.lingshao.secondhand.service.RedisService;
import com.lingshao.secondhand.user.dao.UserDao;
import com.lingshao.secondhand.user.entity.User;
import com.lingshao.util.ComUtil;
import com.lingshao.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Value("${pictureDir}")
    private String pictureDir;

    @Value("${goodsDir}")
    private String goodsDir;

    @Autowired
    private CommodityDao commodityDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RedisService redisService;

    @Override
    public List<Commodity> getCommodity(String conditionStr) {
        System.out.println(commodityDao.getCommodity(conditionStr));
        return commodityDao.getCommodity(conditionStr);
    }

    @Override
    public Commodity getOneCommodity(String commodityId) {
        Commodity commodity = commodityDao.getDetailedCommodity(commodityId);
        if (commodity.getTransaction().equals("0")) {
            commodity.setTransaction(CommonConstant.TRANSACTION.CUSTOM);
        } else if (commodity.getTransaction().equals("1")) {
            commodity.setTransaction(CommonConstant.TRANSACTION.SEND_BY_SELLER);
        } else if (commodity.getTransaction().equals("2")) {
            commodity.setTransaction(CommonConstant.TRANSACTION.TAKE_GOODS_BY_BUYER);
        } else if (commodity.getTransaction().equals("3")) {
            commodity.setTransaction(CommonConstant.TRANSACTION.ACCORD_BUYER);
        } else {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.PARAM_ERROR);
        }
        return commodityDao.getDetailedCommodity(commodityId);
    }

    @Override
    public List<Commodity> getMyCommodity(String stuNumber) {
        return commodityDao.getMyCommodity(stuNumber);
    }

    @Override
    public Integer addOrUpdateCommodity(String stuNumber, Commodity commodity) throws IOException {
        //用户个人信息是否完善
        User user = userDao.getUserInfoById(stuNumber);
        if (ComUtil.isEmpty(user)) {
            throw new BusinessException(ErrCode.USERNAME_ERROR, PublicResultConstant.USER_IS_ERROR);
        }
        if (ComUtil.isEmpty(user.getPhone())) {
            throw new BusinessException(ErrCode.USER_PHONE_NOT_COMPLETE, PublicResultConstant.PHONE_IS_EMPTY);
        }
        if (ComUtil.isEmpty(user.getAddress())) {
            throw new BusinessException(ErrCode.USER_ADDRESS_NOT_COMPLETE, PublicResultConstant.ADDRESS_IS_EMPTY);
        }
        //判断输入信息是否完整
        if (ComUtil.isEmpty(commodity.getCommodityName())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_NAME_EMPTY);
        }
        if (ComUtil.isEmpty(commodity.getNumber())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_NUMBER_ERROR);
        }
        if (StringUtil.isInteger(commodity.getNumber()) == false) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_NUMBER_ERROR);
        }
        if (ComUtil.isEmpty(commodity.getDescribe())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_DESCRIBE_EMPTY);
        }
        if (ComUtil.isEmpty(commodity.getPrice())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_PRICE_EMPTY);
        }
        if (ComUtil.isEmpty(commodity.getTransaction())) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.COMMODITY_TRANSACTION_EMPTY);
        }
        if (commodity.getCommodityId() == null) {
            //新增
            return commodityDao.addCommodity(stuNumber, commodity);
        } else {
            //判断是否产生订单，如果产生无法修改
            Set<String> keysAllSet = redisService.getKeysAll();
            List<Order> ordersList = new ArrayList<>(8);
            if (!ComUtil.isEmpty(keysAllSet)) {
                Map<String, String> getValues = redisService.getAllValue(keysAllSet);
                for (String key : getValues.keySet()) {
                    Order order = JSONObject.parseObject(getValues.get(key), Order.class);
                    ordersList.add(order);
                }
            }
            List<Order> Temp = ordersList.stream()
                    .filter(e -> e.getGoodsId().equals(commodity.getCommodityId())).collect(Collectors.toList());
            if (!ComUtil.isEmpty(Temp)) {
                throw new BusinessException(ErrCode.ACCESS_LIMITED, PublicResultConstant.ORDER_IS_EXIST);
            }
            Temp = orderDao.getOrderByGoodsId(commodity.getCommodityId());
            if (!ComUtil.isEmpty(Temp)) {
                throw new BusinessException(ErrCode.ACCESS_LIMITED, PublicResultConstant.ORDER_IS_EXIST);
            }
            return commodityDao.updateCommodity(stuNumber, commodity);
        }

    }

    @Override
    public Integer deleteMyCommodity(String stuNumber, String commodityId) {
        Set<String> keysAllSet = redisService.getKeysAll();
        List<Order> ordersList = new ArrayList<>(8);
        if (!ComUtil.isEmpty(keysAllSet)) {
            Map<String, String> getValues = redisService.getAllValue(keysAllSet);
            for (String key : getValues.keySet()) {
                Order order = JSONObject.parseObject(getValues.get(key), Order.class);
                ordersList.add(order);
            }
        }
        List<Order> Temp = ordersList.stream()
                .filter(e -> e.getGoodsId().equals(commodityId)).collect(Collectors.toList());
        if (!ComUtil.isEmpty(Temp)) {
            throw new BusinessException(ErrCode.ACCESS_LIMITED, PublicResultConstant.ORDER_IS_EXIST);
        }
        Temp = orderDao.getOrderByGoodsId(commodityId);
        if (!ComUtil.isEmpty(Temp)) {
            throw new BusinessException(ErrCode.ACCESS_LIMITED, PublicResultConstant.ORDER_IS_EXIST);
        }
        commodityDao.deleteCommodity(stuNumber, commodityId);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public Integer deleteLotMyCommodity(String stuNumber, String commodityId) {
        List<String> commodityIdList = JSONObject.parseArray(commodityId, String.class);
        if (ComUtil.isEmpty(commodityIdList)) {
            throw new BusinessException(ErrCode.ARGS_ERROR, PublicResultConstant.PARAM_ERROR);
        }
        commodityDao.deleteLotCommodity(stuNumber, commodityIdList);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public Integer adminDeleteCommodity(String commodityId) {
        commodityDao.adminDeleteCommodity(commodityId);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }
}
