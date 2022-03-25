package com.lingshao.secondhand.util;

import com.alibaba.fastjson.JSONObject;
import com.lingshao.secondhand.order.dao.OrderDao;
import com.lingshao.secondhand.order.entry.Order;
import com.lingshao.secondhand.service.RedisService;
import com.lingshao.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ScheduleUtil {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisService redisService;

        @Scheduled(cron = "0 0 */2 * * ?")
//    @Scheduled(cron = "*/30 * * * * ?")
    public void redisToMySQL() {
        Set<String> keysAllSet = redisService.getKeysAll();
        List<Order> ordersList = new ArrayList<>(8);
        if (!ComUtil.isEmpty(keysAllSet)) {
            System.out.println("订单不为空，正在从缓存中取值");
            Map<String, String> getValues = redisService.getAllValue(keysAllSet);
            for (String key : getValues.keySet()) {
                Order order = JSONObject.parseObject(getValues.get(key), Order.class);
                ordersList.add(order);
            }
            orderDao.addOrder(ordersList);
            System.out.println("持久化完成");
            redisService.flushDb();
        }
        System.out.println("缓存订单为空");
    }
}
