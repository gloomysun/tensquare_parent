package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utlis.SmsUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsListner {
    @Value("${aliyun.sms.template_code}")
    private String template_code;
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;
    @Autowired
    private SmsUtils smsUtils;


    @RabbitListener(queues = "sms")
    public void process(Map<String, String> map) {
        System.out.println("mobile:" + map.get("mobile"));
        System.out.println("mobile:" + map.get("code"));
        String mobile = map.get("mobile");
        String code = map.get("code");
        try {
            smsUtils.sendSms(mobile, template_code, sign_name, "{\"code\":" + code + "}");
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
