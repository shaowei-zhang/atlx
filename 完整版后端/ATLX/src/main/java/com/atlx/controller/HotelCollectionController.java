package com.atlx.controller;

import com.atlx.dao.IHotelCollectionMapper;
import com.atlx.dao.IHotelMapper;
import com.atlx.pojo.FoodCollection;
import com.atlx.pojo.Hotel;
import com.atlx.pojo.HotelCollection;
import com.atlx.pojo.User;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IHotelService;
import com.atlx.service.impl.HotelCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotelCollection")
public class HotelCollectionController {
    @Autowired
    private HotelCollectionService hotelCollectionService;
    @Autowired
    private IHotelService iHotelService;
    @Autowired
    private IHotelCollectionMapper iHotelCollectionMapper;

    @PostMapping("/insertCollection")
    public RestBean<HotelCollection> insertCollection(@RequestBody Map map){

        String hotelName = map.get("hotelName").toString();

        Hotel hotel = iHotelService.queryIdByName(hotelName);
        HotelCollection hotelCollection = new HotelCollection();
        hotelCollection.setHotelId(hotel.getId());
        hotelCollection.setAccount(map.get("account").toString());
        int insert = iHotelCollectionMapper.insert(hotelCollection);
        return new RestBean(200,"正常",insert);
    }
    //联表查询 hotelCollection hotel
    @PostMapping("/queryAllMessage")
    public RestBean<User> queryAllMessage(@RequestBody Map map){
        List<HotelCollection> hotelCollection = hotelCollectionService.queryAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",hotelCollection);
    }


    /*
     *
     * 删除操作
     *
     * */
    @DeleteMapping("/deleteHotelById/{dels}")
    public RestBean<User> deleteFoodLocalById(@PathVariable Integer[] dels){
        int m=0;
        for (int i = 0; i < dels.length; i++) {
           m= iHotelCollectionMapper.deleteById(dels[i]);
        }
        return new RestBean(200,"正常",m);
    }

}
