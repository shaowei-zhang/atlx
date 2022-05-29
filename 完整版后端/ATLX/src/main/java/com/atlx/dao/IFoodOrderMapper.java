package com.atlx.dao;

import com.atlx.pojo.FoodOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface IFoodOrderMapper extends BaseMapper<FoodOrder> {

    @Update("update food_order set state=#{state},payment_time=#{paymentTime},alipay_no=#{alipayNo} where number=#{number}")
    int updateState(@Param("number") String number,@Param("state") String state,@Param("paymentTime") String gmtPayment,
                    @Param("alipayNo") String alipayTradeNo);

    public List<FoodOrder> findAll();
    public List<FoodOrder> findByAccount(String account);
//    @Override
//    List<FoodOrder> selectList(Wrapper<FoodOrder> queryWrapper);
//
//    @Override
//    int deleteById(Serializable id);//删除
//
//    @Override
//    int deleteByMap(Map<String, Object> columnMap);//批量删除
//
//    @Override
//    int updateById(FoodOrder entity);
//
//    @Override
//    int insert(FoodOrder entity);//插入
}
