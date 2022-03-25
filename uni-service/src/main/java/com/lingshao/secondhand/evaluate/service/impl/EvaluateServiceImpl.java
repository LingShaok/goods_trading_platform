package com.lingshao.secondhand.evaluate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lingshao.constant.CommonConstant;
import com.lingshao.constant.ErrCode;
import com.lingshao.entity.PublicResultConstant;
import com.lingshao.secondhand.evaluate.dao.EvaluateDao;
import com.lingshao.secondhand.evaluate.entity.Evaluate;
import com.lingshao.secondhand.evaluate.service.EvaluateService;
import com.lingshao.secondhand.exception.BusinessException;
import com.lingshao.secondhand.order.dao.OrderDao;
import com.lingshao.secondhand.order.entry.Order;
import com.lingshao.secondhand.service.RedisService;
import com.lingshao.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private EvaluateDao evaluateDao;

    @Autowired
    private RedisService redisService;

    @Override
    public Integer addEvaluate(String userNumber,Evaluate evaluate) {
        Order redisOrder = JSONObject.parseObject(redisService.get(evaluate.getOrderNumber()),Order.class);
        if (!ComUtil.isEmpty(redisOrder)){
            throw new BusinessException(ErrCode.DEAL_NOT_COMPLETE, PublicResultConstant.DEAL_NOT_COMPLETE);
        }
        Order dataOrder =orderDao.getOrderByOrderNum(evaluate.getOrderNumber());
        if (ComUtil.isEmpty(dataOrder)){
            throw new BusinessException(ErrCode.UNKOWN_ERROR,PublicResultConstant.ORDER_NOT_EXIST);
        }
        if (!dataOrder.getBuyerId().equals(userNumber)){
            throw new BusinessException(ErrCode.ACCESS_LIMITED,PublicResultConstant.NO_DEAL_NO_EVALUATE);
        }
        if (dataOrder.getIsPayBuyer().equals("0")||dataOrder.getIsPaySeller().equals("0")){
            throw new BusinessException(ErrCode.DEAL_NOT_COMPLETE,PublicResultConstant.DEAL_NOT_COMPLETE);
        }
        evaluate.setBuyerId(userNumber);
        Order order = orderDao.getOrderByOrderNum(evaluate.getOrderNumber());
        evaluate.setSellerId(order.getSellerId());
        evaluateDao.addEvaluate(evaluate);
        return CommonConstant.MESSAGE_CODE.SUCCESS;
    }

    @Override
    public List<Evaluate> getEvaluateBySellerId(String sellerId) {
        return evaluateDao.getEvaluateBySellerId(sellerId);
    }

    @Override
    public List<Evaluate> getEvaluateByBuyerId(String buyerId) {
        return evaluateDao.getEvaluateByBuyerId(buyerId);
    }

    @Override
    public Integer delEvaluate(String evaluateId, String buyerId) {
        return evaluateDao.delEvaluate(evaluateId,buyerId);
    }

    @Override
    public Integer isEvaluate(String userNumber, String order) {
        Integer result = evaluateDao.isEvaluate(order,userNumber);
        if (ComUtil.isEmpty(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
