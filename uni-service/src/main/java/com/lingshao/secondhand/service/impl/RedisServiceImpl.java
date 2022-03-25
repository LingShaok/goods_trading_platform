package com.lingshao.secondhand.service.impl;

import com.lingshao.secondhand.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Value("${spring.redis.database}")
    private Integer database;

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }


    @Override
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public String getOtherDataBase(String key, Integer otherBase) {
        jedisConnectionFactory.setDatabase(otherBase);
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        jedisConnectionFactory.setDatabase(database);
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        System.out.println("ok");
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean dataExpire(String key, Integer expire) {
        redisTemplate.expire(key,expire,TimeUnit.SECONDS);
        Jedis jedis = new Jedis();
        jedis.expire(key,expire);
        return false;
    }

    @Override
    public boolean remove(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean removeOtherDataBase(String key, Integer otherBase) {
        jedisConnectionFactory.setDatabase(otherBase);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        jedisConnectionFactory.setDatabase(database);
        return result;
    }

    @Override
    public Set<String> getKeysAll() {
        jedisConnectionFactory.setDatabase(database);
        Set<String> result = stringRedisTemplate.keys("*");
        return result;
    }

    @Override
    public boolean flushDb() {
        jedisConnectionFactory.setDatabase(database);
        Jedis jedis = new Jedis();
        jedis.flushDB();
//        redisTemplate.delete(Keys);
        return true;
    }

    @Override
    public Map<String,String> getAllValue(Set<String> keys) {
        HashMap<String,String> map = new HashMap<>();
        for (String key : keys) {
            String value = get(key);
            map.put(key, value);
        }
        return map;
    }
}
