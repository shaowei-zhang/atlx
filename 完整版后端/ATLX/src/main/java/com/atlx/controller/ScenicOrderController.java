package com.atlx.controller;

import com.atlx.pojo.Scenic;
import com.atlx.pojo.ScenicOrder;
import com.atlx.pojo.ScenicOrder;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IScenicOrderService;
import com.atlx.service.IScenicService;
import com.atlx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/scenicOrder")
public class ScenicOrderController {
    @Autowired
    IScenicOrderService iScenicOrderService;
    @Autowired
    IScenicService iScenicService;
    @Autowired
    IUserService iUserService;

    @PostMapping("/orderList")
    public RestBean<ScenicOrder> scenicOrderList(@RequestBody Map searchAccount){
        String account = searchAccount.get("account").toString();

        List<ScenicOrder> scenicOrderList = iScenicOrderService.findAll();


        //查询时触发
        for(int i=0;i<scenicOrderList.size();i++){
            if (scenicOrderList.get(i).getUser().getAccount().equals(account)){
                List<ScenicOrder> scenicOrders = iScenicOrderService.findByAccount(account);
                return new RestBean(200,"请求成功",scenicOrders);
            }
        }
        
        
        return new RestBean(200,"请求成功",scenicOrderList);
    }

    /**
     * 个人中心订单列表
     * @param searchAccount
     * @return
     */
    @PostMapping("/personOrderList")
    public RestBean<ScenicOrder> personOrderList(@RequestBody Map searchAccount){
        String account = searchAccount.get("account").toString();

        List<ScenicOrder> scenicOrders = iScenicOrderService.findByAccount(account);
        return new RestBean(200,"请求成功",scenicOrders);

    }

    @PostMapping("/delOrder/{dels}")
    public RestBean<Void> delOrder(@PathVariable("dels") Integer[] dels){
        int i = 0;
        for (;i< dels.length;i++){
            iScenicOrderService.deleteById(dels[i]);
        }

        if(i == dels.length){
            return new RestBean(200,"删除成功",null);
        }else {
            return new RestBean(500,"删除失败",null);
        }

    }

    @PostMapping("/submitOrder")
    public RestBean insertScenicOrder(@RequestBody Map map) {
        String scenicName = map.get("scenicName").toString();
        String account = map.get("account").toString();
        /*根据foodName查询foodlcoalId*/
        Scenic scenic = iScenicService.queryIdByName(scenicName);
        /*产生一个随机数*/
        Random random = new Random();
        String i = random.nextInt(100000)+"";
        /*输入当前的时间*/
        Date date = new Date();
        /*获取全局变量account,查询id*/
        ScenicOrder scenicOrder = new ScenicOrder();
        scenicOrder.setScenicId(scenic.getId());
        scenicOrder.setNumber(i);
        scenicOrder.setPlaceOrderDatetime(date);
        scenicOrder.setUserId(iUserService.findUserByAccount(account).get(0).getId());
        if(iScenicOrderService.insertOne(scenicOrder)){
            return new RestBean(200,"预定成功",null);

        }
        return new RestBean(500,"预定失败",null);
    }
}
