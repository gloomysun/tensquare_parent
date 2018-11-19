package com.tensquare.user.service;

import com.tensquare.entity.Result;
import com.tensquare.user.entity.User;
import com.tensquare.user.repository.UserRepository;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;

    public void sendSms(String mobile) {
        String code = RandomStringUtils.randomNumeric(6);
        System.out.println("生成的验证码:" + code);
        //存入redis
        redisTemplate.opsForValue().set("smscode:" + mobile, code, 5, TimeUnit.MINUTES);
        //发送到mq
        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("code", code);
        rabbitTemplate.convertAndSend("sms", map);
    }

    public void add(User user, String code) {
        String syscode = (String) redisTemplate.opsForValue().get("smscode:" + user.getMobile());
        if (syscode == null) {
            throw new RuntimeException("请先获取验证码");
        } else if (!syscode.equals(code)) {
            throw new RuntimeException("请输入正确的验证码");
        }
        user.setId(idWorker.nextId());
        userRepository.save(user);
    }
}
