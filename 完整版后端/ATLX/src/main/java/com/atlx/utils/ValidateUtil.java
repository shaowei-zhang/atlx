package com.atlx.utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;


/**
 * 验证码工具包
 */
@Component
public class ValidateUtil {
    /*** 生成验证码图片
     * @param request 设置session
     * @param response 转成图片
     * @param captchaProducer 生成图片方法类
     * @param validateSessionKey session名称
     * @throws Exception
     * */

    @Autowired
    StringRedisTemplate redisTemplate;


    public void validateCode(HttpServletResponse response, DefaultKaptcha captchaProducer, String validateSessionKey) throws Exception{

        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must- revalidate");
        // Set IE extended HTTP/1.1 no-cache headers(use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        // store the text in the session

        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(validateSessionKey,capText);
        redisTemplate.expire(validateSessionKey,60, TimeUnit.SECONDS);

//        request.getSession().setAttribute(validateSessionKey,capText);
// create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
// write the data out
        ImageIO.write(bi, "jpg", out); try {out.flush(); } finally { out.close(); } }
}