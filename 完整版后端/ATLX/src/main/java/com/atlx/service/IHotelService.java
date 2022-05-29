package com.atlx.service;

/**
 * @author zhangshaowei
 * @date 2022/5/22 13:05
 * @description
 */
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.Hotel;

import java.util.List;

public interface IHotelService {
    List<Hotel> queryHotel();
    //多表查询
    List<Hotel> query();
    Hotel queryHotelById(Integer id);
    //添加
    boolean addHotel(Hotel hotel);
    //修改
    boolean updateHotel(Hotel hotel);
    //删除
    boolean delHotel(Integer id);
    List<Hotel> queryByName(String hotelName);
    List<String> queryHotelImg();
    List<Hotel> queryHotelComment(String hotelName);
    Hotel queryIdByName(String hotelName);

    /*这个是给个人中心的评论*/
    List<Hotel>  queryComment(String account);
}
