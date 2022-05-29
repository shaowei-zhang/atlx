package com.atlx.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 *
 * 验证码config
 *
 */
@Component
public class ValidateConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha()
    { DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
         //图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "rgba(255, 255, 255, 0)");
        // 边框厚度
        properties.setProperty("kaptcha.border.thickness", "0.5");
        // 图片宽
//        properties.setProperty("kaptcha.image.width", "200");
        // 图片高
        properties.setProperty("kaptcha.image.height", "40");
        // 图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");
        // 文本实现类
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        // 文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string", "AEFHKPRWX13456789");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "Arial");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        // 文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        // 干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        // 干扰颜色
        properties.setProperty("kaptcha.noise.color", "black");
        // 干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        // 背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to", "pink");
        // 文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer"); Config config = new Config(properties); defaultKaptcha.setConfig(config); return defaultKaptcha;

    }
}
