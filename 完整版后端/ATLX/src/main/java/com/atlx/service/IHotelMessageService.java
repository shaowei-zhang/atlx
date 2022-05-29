package com.atlx.service;

import com.atlx.pojo.FoodMessage;
import com.atlx.pojo.HotelMessage;

import java.util.List;

public interface IHotelMessageService {
    List<HotelMessage> findAllByName(String hotelName);
    List<HotelMessage> findAllByAccount(String account);
}
