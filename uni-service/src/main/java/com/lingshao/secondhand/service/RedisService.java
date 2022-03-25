package com.lingshao.secondhand.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {

    /**
     * set存数据
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * get获取数据
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * get获取数据
     *
     * @param key
     * @return
     */
    String getOtherDataBase(String key, Integer getOtherDataBase);

    /**
     * 设置有效天数
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    /**
     * @description 设置有效时间，秒
     * @author LingShao
     * @date 2021/5/6
     * @param @param null:
     * @return
     */
    boolean dataExpire(String key, Integer second);
    /**
     * 移除数据
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 移除数据
     *
     * @param key
     * @return
     */
    boolean removeOtherDataBase(String key, Integer otherBase);


    /**
     * keys *
     * @return
     */
    Set<String> getKeysAll();

    /**
     * 清空表
     * @return
     */
//    boolean flushDb(List<String> Keys);
    boolean flushDb();

    /**
     * 获取所有的值
     * @return
     */
    Map<String,String> getAllValue(Set<String> keys);
}