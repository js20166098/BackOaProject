package com.js.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: 姜爽
 * @Description: 相关redis的操作
 * @Date: 2020/5/11 11:29
 */
@Service
@Slf4j
@SuppressWarnings("unchecked")
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用户登录
     **/
    public void login(String studentNumber, String token) {
        redisTemplate.opsForValue().set(studentNumber, token);
        redisTemplate.expire(studentNumber, 15, TimeUnit.MINUTES);
    }

    /**
     * 用户注销登陆
     **/
    public Boolean logOut(String studentNumber) {
        return redisTemplate.delete(studentNumber);
    }

    /**
     * 获取登陆用户的Token
     **/
    public String getToken(String studentNumber) {
        log.info("查询用户学号{}", studentNumber);
        return redisTemplate.opsForValue().get(studentNumber).toString();
    }

    /**
     * 邮箱验证码验证
     **/
    public void setMailCode(String studentNumber, String code) {
        redisTemplate.opsForValue().set(studentNumber, code);
        redisTemplate.expire(studentNumber, 120, TimeUnit.SECONDS);
    }

    /**
     * 用户验证码（修改密码时使用）
     **/
    public void rePassCode(String studentNumber, String code) {
        redisTemplate.opsForValue().set("oa" + studentNumber, code);
        redisTemplate.expire("oa" + studentNumber, 2, TimeUnit.MINUTES);
    }

    /**
     * 用户验证码（修改密码时使用）
     **/
    public String getPassCode(String studentNumber) {
        String rePassCode = String.valueOf(redisTemplate.opsForValue().get("oa" + studentNumber));
        redisTemplate.delete("oa" + studentNumber);
        return rePassCode;
    }
}
