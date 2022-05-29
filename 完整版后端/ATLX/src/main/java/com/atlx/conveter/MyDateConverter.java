package com.atlx.conveter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//1、实现Converter接口
public class MyDateConverter implements Converter<String, Date> {
    private SimpleDateFormat sdf;
    //构造方法传入转换格式
    public MyDateConverter(String formatter){
        this.sdf = new SimpleDateFormat(formatter);
    }

    @Override
    public Date convert(String source) {
        Date date = null;
        try {
             date = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
