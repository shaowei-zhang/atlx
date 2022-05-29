package com.atlx.controller;
import com.atlx.pojo.HotelOrder;
import com.atlx.pojo.Hotel;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IHotelOrderService;
import com.atlx.service.IHotelService;
import com.atlx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/hotelOrder")
public class HotelOrderController {
    @Autowired
    IHotelOrderService iHotelOrderService;
    @Autowired
    IHotelService iHotelService;
    @Autowired
    IUserService iUserService;

    @PostMapping("/orderList")
    public RestBean<HotelOrder> hotelOrderList(@RequestBody Map searchAccount){
        String account = searchAccount.get("account").toString();
        List<HotelOrder> hotelOrderList = iHotelOrderService.findAll();

        //查询时触发
        for(int i=0;i<hotelOrderList.size();i++){
            if (hotelOrderList.get(i).getUser().getAccount().equals(account)){
                List<HotelOrder> hotelOrders = iHotelOrderService.findByAccount(account);
                return new RestBean(200,"请求成功",hotelOrders);
            }
        }


        return new RestBean(200,"请求成功",hotelOrderList);
    }


    /**
     * 个人中心订单列表
     * @param searchAccount
     * @return
     */
    @PostMapping("/personOrderList")
    public RestBean<HotelOrder> personOrderList(@RequestBody Map searchAccount){
        String account = searchAccount.get("account").toString();

        List<HotelOrder> hotelOrders = iHotelOrderService.findByAccount(account);
        return new RestBean(200,"请求成功",hotelOrders);

    }

    @PostMapping("/delOrder/{dels}")
    public RestBean<Void> delOrder(@PathVariable("dels") Integer[] dels){
        int i = 0;
        for (;i< dels.length;i++){
            iHotelOrderService.deleteById(dels[i]);
        }

        if(i == dels.length){
            return new RestBean(200,"删除成功",null);
        }else {
            return new RestBean(500,"删除失败",null);
        }

    }

    @PostMapping("/submitOrder")
    public RestBean insertHotelOrder(@RequestBody Map map) {
        String hotelName = map.get("hotelName").toString();
        String account = map.get("account").toString();
        /*根据foodName查询foodlcoalId*/
        Hotel hotel = iHotelService.queryIdByName(hotelName);
        /*产生一个随机数*/
        Random random = new Random();
        String i = random.nextInt(100000)+"";
        /*输入当前的时间*/
        Date date = new Date();
        /*获取全局变量account,查询id*/
        HotelOrder hotelOrder = new HotelOrder();
        hotelOrder.setHotelId(hotel.getId());
        hotelOrder.setNumber(i);
        hotelOrder.setPlaceOrderDatetime(date);
        hotelOrder.setUserId(iUserService.findUserByAccount(account).get(0).getId());
        if(iHotelOrderService.insertOne(hotelOrder)){
            return new RestBean(200,"预定成功",null);

        }
        return new RestBean(500,"预定失败",null);
    }
}
