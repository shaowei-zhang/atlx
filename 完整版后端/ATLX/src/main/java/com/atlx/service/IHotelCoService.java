package com.atlx.service;

/**
 * @author zhangshaowei
 * @date 2022/5/22 13:04
 * @description
 */
import com.atlx.pojo.HotelCollection;

import java.util.List;

public interface IHotelCoService {
    List<HotelCollection> queryHotelCollection();
    HotelCollection queryById(Integer id);
    boolean addHotelCollection(HotelCollection hotelCollection);
    boolean updateHotelCollection(HotelCollection hotelCollection);
    boolean delHotelCollection(Integer id);
    List<HotelCollection> queryAllByAccount(String account);
}
