package com.atlx.dao;

/**
 * @author zhangshaowei
 * @date 2022/5/22 13:00
 * @description
 */
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.Hotel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IHotelMapper extends BaseMapper<Hotel> {
    List<Hotel> queryHotel();
    List<Hotel> queryByName(String hotelName);
    List<String> queryHotelImg();

    /*
     * 查询酒店的评论
     * */
    List<Hotel> queryHotelComment(String hotelName);
    /*根据酒店名来查询id*/
    Hotel queryIdByName(String hotelName);

    List<Hotel>  queryComment(String account);
}
