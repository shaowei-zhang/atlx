package com.atlx.controller;

import com.atlx.dao.IHotelMessageMapper;
import com.atlx.pojo.Hotel;
import com.atlx.pojo.HotelMessage;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IHotelMessageService;
import com.atlx.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotelMessage")
public class HotelMessageController {
    @Autowired
    private IHotelMessageMapper iHotelMessageMapper;
    @Autowired
    private IHotelMessageService service;
    @Autowired
    IHotelService iHotelService;

    @PostMapping("/findAllByAccount")
    public RestBean<HotelMessage> findAllByAccount(@RequestBody Map map){
        List<HotelMessage> hotelMessages = service.findAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",hotelMessages);
    }

    @PostMapping("/findAllByName")
    public RestBean<HotelMessage> findAllByName(@RequestBody Map map){
        String hotelName = map.get("hotelName").toString();
        List<HotelMessage> hotelMessages = iHotelMessageMapper.findAllByName(hotelName);
        return new RestBean(200,"正常",hotelMessages);
    }


    @PostMapping("/insertHotelMessage")
    public RestBean<HotelMessage> inserthotelLocal(@RequestBody Map message){
        HotelMessage hotelMessage = new HotelMessage();
        String hotelName = message.get("hotelName").toString();

        Hotel hotel= iHotelService.queryIdByName(hotelName);

        hotelMessage.setHotelId(hotel.getId());
        hotelMessage.setAccount(message.get("account").toString());
        hotelMessage.setCommentDate(new Date());
        hotelMessage.setComment(message.get("comment").toString());
        int insert = iHotelMessageMapper.insert(hotelMessage);
        return new RestBean(200,"正常",insert);
    }


    @DeleteMapping("/deleteHotelMessageById/{dels}")
    public RestBean<HotelMessage> deleteHotelLocalById(@PathVariable Integer[] dels){

        int m=0;
        for (int i = 0; i < dels.length; i++) {
            m =  iHotelMessageMapper.deleteById(dels[i]);
        }

        return new RestBean(200,"正常",m);
    }
}
