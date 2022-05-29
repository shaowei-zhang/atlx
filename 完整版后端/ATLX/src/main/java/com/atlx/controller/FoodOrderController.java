package com.atlx.controller;

import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.FoodOrder;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IFoodLocalService;
import com.atlx.service.IFoodOrderService;
import com.atlx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/foodOrder")
public class FoodOrderController {

    @Autowired
    IFoodOrderService iFoodOrderService;
    @Autowired
    private IFoodLocalService iFoodLocalService;
    @Autowired
    IUserService iUserService;

    @PostMapping("/orderList")
    public RestBean<FoodOrder> foodOrderList(@RequestBody Map searchAccount){

        String account = searchAccount.get("account").toString();
        List<FoodOrder> foodOrderList = iFoodOrderService.findAll();


        //查询时触发
        for(int i=0;i<foodOrderList.size();i++){
            if (foodOrderList.get(i).getUser().getAccount().equals(account)){
                List<FoodOrder> foodOrders = iFoodOrderService.findByAccount(account);
                return new RestBean(200,"请求成功",foodOrders);
            }
        }

        return new RestBean(200,"请求成功",foodOrderList);

    }

    /**
     * 个人中心订单列表
     * @param searchAccount
     * @return
     */
    @PostMapping("/personOrderList")
    public RestBean<FoodOrder> personOrderList(@RequestBody Map searchAccount){
        String account = searchAccount.get("account").toString();

        List<FoodOrder> foodOrders = iFoodOrderService.findByAccount(account);
        return new RestBean(200,"请求成功",foodOrders);

    }

    @PostMapping("/delOrder/{dels}")
    public RestBean<Void> delOrder(@PathVariable("dels") Integer[] dels){
        int i = 0;
        for (;i< dels.length;i++){
            iFoodOrderService.deleteById(dels[i]);
        }

        if(i == dels.length){
            return new RestBean(200,"删除成功",null);
        }else {
            return new RestBean(500,"删除失败",null);
        }

    }

    @PostMapping("/submitOrder")
    public RestBean insertFoodOrder(@RequestBody Map map) {

        String foodName = map.get("foodName").toString();
        String account = map.get("account").toString();

        /*根据foodName查询foodlcoalId*/
        FoodLocal foodLocal = iFoodLocalService.queryFoodLocalByName(foodName);
        /*产生一个随机数*/
        Random random = new Random();
        String i = random.nextInt(100000)+"";
        /*输入当前的时间*/
        Date date = new Date();
        /*获取全局变量account,查询id*/
        FoodOrder foodOrder = new FoodOrder();
        foodOrder.setFoodLocalId(foodLocal.getId());
        foodOrder.setNumber(i);
        foodOrder.setPlaceOrderDatetime(date);
        foodOrder.setUserId(iUserService.findUserByAccount(account).get(0).getId());
        if(iFoodOrderService.insertOne(foodOrder)){
            return new RestBean(200,"预定成功",null);

        }
        return new RestBean(500,"预定失败",null);
    }

}
