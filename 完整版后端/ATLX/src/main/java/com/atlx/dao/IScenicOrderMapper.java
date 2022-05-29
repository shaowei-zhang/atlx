package com.atlx.dao;

import com.atlx.pojo.FoodOrder;
import com.atlx.pojo.Scenic;
import com.atlx.pojo.ScenicOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IScenicOrderMapper extends BaseMapper<ScenicOrder> {

    @Update("update hotel_order set state=#{state},payment_time=#{paymentTime},alipay_no=#{alipayNo} where number=#{number}")
    int updateState(@Param("number") String number, @Param("state") String state, @Param("paymentTime") String gmtPayment,
                    @Param("alipayNo") String alipayTradeNo);
    public List<ScenicOrder> findAll();
    public List<ScenicOrder> findByAccount(String account);
}
