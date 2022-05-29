package com.atlx.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 电子邮箱验证码发送以及获取
 */
@Component
public class EmailVerify {
    @Autowired
    JavaMailSender sender;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    String from;

    public boolean sendVerifyCode(String email) {


        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("【安途旅行】验证码：");

        Random random = new Random();
        int verifyCode = random.nextInt(899999)+100000;


        try{     //需要安装redis
            redisTemplate.opsForValue().set("verifyCode:"+email,verifyCode+"",1, TimeUnit.MINUTES);
        }catch (Exception e){
            System.out.println(e);
        }

        message.setText("您的验证码为："+verifyCode+ "  " +"温馨提示：有效时间仅为一分钟！");

        message.setTo(email);//将验证码发送到对应的邮箱
        message.setFrom(from);//非必须
        try{
            sender.send(message);
            return true;
        }catch (Exception e){
            System.out.println("发送邮件异常为："+e);
            return false;
        }

    }

//    public boolean doVerify(String email, String verifyCode) {
//        String string = null;
//        try{
//            string = redisTemplate.opsForValue().get("verifyCode:"+email);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//
//        if(string == null) return false;
////        System.out.println("string:"+string+",verifyCode:"+verifyCode);
//        if(!string.equals(verifyCode)) return false;
//        redisTemplate.delete("verifyCode:"+email);
//        return true;
//    }
}
