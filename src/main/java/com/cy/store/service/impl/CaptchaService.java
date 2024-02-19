package com.cy.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CaptchaService {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public CaptchaService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public Map<String, String> createCaptcha() {
        // 生成3到5位的随机数验证码
        Random random = new Random();
        int length = random.nextInt(3) + 3; // 3-5隨機
        String captcha = random.ints(length, 0, 10)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        // 驗證碼key
        String captchaKey = "captcha:" + UUID.randomUUID().toString();

        // 驗證碼存到redis,60秒到期
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(captchaKey, captcha, 60, TimeUnit.SECONDS);


        Map<String, String> captchaMap = new HashMap<>();
        captchaMap.put("captchaKey", captchaKey);
        captchaMap.put("captcha", captcha);


        return captchaMap;
    }
}
