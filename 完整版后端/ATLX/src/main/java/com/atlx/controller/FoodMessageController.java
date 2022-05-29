package com.atlx.controller;

import com.atlx.dao.IFoodMessageMapper;
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.FoodMessage;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IFoodLocalService;
import com.atlx.service.IFoodMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/foodMessage")
public class FoodMessageController {
    @Autowired
    private IFoodMessageMapper iFoodMessageMapper;
    @Autowired
    private IFoodMessageService iFoodMessageService;
    @Autowired
    IFoodLocalService iFoodLocalService;
    
    @PostMapping("/findAllByAccount")
    public RestBean<FoodMessage> findAllByAccount(@RequestBody Map map){

        List<FoodMessage> zhangsan = iFoodMessageService.findAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",zhangsan);
    }

    @PostMapping("/findAllByName")
    public RestBean<FoodMessage> findAllByName(@RequestBody Map map){
        String foodName = map.get("foodName").toString();
        List<FoodMessage> FoodMessages = iFoodMessageMapper.findAllByName(foodName);
        return new RestBean(200,"正常",FoodMessages);
    }


    @PostMapping("/insertFoodMessage")
    public RestBean<FoodMessage> insertFoodLocal(@RequestBody Map message){
        FoodMessage foodMessage = new FoodMessage();
        String foodName = message.get("foodName").toString();

        FoodLocal food = iFoodLocalService.queryFoodLocalByName(foodName);

        foodMessage.setFoodLocalId(food.getId());
        foodMessage.setAccount(message.get("account").toString());
        foodMessage.setCommentDate(new Date());
        foodMessage.setComment(message.get("comment").toString());
        int insert = iFoodMessageMapper.insert(foodMessage);
        return new RestBean(200,"正常",insert);
    }

    
    @DeleteMapping("/deleteFoodMessageById/{dels}")
    public RestBean<FoodMessage> deleteFoodLocalById(@PathVariable Integer[] dels){

        int m=0;
        for (int i = 0; i < dels.length; i++) {
             m = iFoodMessageMapper.deleteById(dels[i]);
        }

        return new RestBean(200,"正常",m);
    }


}
