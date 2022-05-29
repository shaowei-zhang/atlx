package com.atlx.controller;

import com.atlx.dao.IFoodCollectionMapper;
import com.atlx.pojo.FoodCollection;
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.User;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IFoodCollectionService;
import com.atlx.service.IFoodLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshaowei
 * @date 2022/5/10 15:33
 * @description
 */
@RequestMapping("/foodCollection")
@RestController
public class FoodCollectionController {

    @Autowired
    private IFoodCollectionMapper iFoodCollectionMapper;
    @Autowired
    private IFoodLocalService iFoodLocalService;
    @Autowired
    private IFoodCollectionService iFoodCollectionService;

    //联表查询 foodCollection FoodCollection
    @PostMapping("/queryAllMessage")
    public RestBean<User> queryAllMessage(@RequestBody Map map){
        List<FoodCollection> collection = iFoodCollectionService.queryAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",collection);
    }
    //根据id 查询
    //根据id 查询
    @GetMapping("/queryById/{id}")
    public RestBean<User> queryById(@PathVariable Integer id){
       FoodCollection foodCollection = iFoodCollectionMapper.selectById(id);
        return new RestBean(200,"正常",foodCollection);
    }

    /*
    *
    * 执行收藏功能
    *
    * */
    @PostMapping("/collectionFood")
    public RestBean<User> collectionFood(@RequestBody Map map){
        String foodName = map.get("foodName").toString();
        FoodLocal foodLocal = iFoodLocalService.queryFoodLocalByName(foodName);
        Integer id = foodLocal.getId();
        FoodCollection foodCollection = new FoodCollection();
        foodCollection.setFoodLocalId(id);
        foodCollection.setAccount(map.get("account").toString());
        int insert = iFoodCollectionMapper.insert(foodCollection);

        return new RestBean(200,"正常",insert);
    }

    /*
     * 插入
     *
     * */

    //仅插入FoodCollection表
    @PostMapping("/insertFoodCollection")
    public RestBean<User> insertFoodLocal(@RequestBody FoodCollection foodCollection){
        int insert = iFoodCollectionMapper.insert(foodCollection);
        return new RestBean(200,"正常",insert);
    }


    /*
     *
     * 删除操作
     *
     * */
    @DeleteMapping("/deleteFoodCollectionById/{dels}")
    public RestBean<User> deleteFoodLocalById(@PathVariable Integer[] dels){
        int m=0;
        for (int i = 0; i < dels.length; i++) {
            m= iFoodCollectionMapper.deleteById(dels[i]);
        }
        return new RestBean(200,"正常",m);
    }


    @DeleteMapping("/deleteFoodById/{id}")
    public RestBean<User> deleteFoodById(@PathVariable Integer id){
        int i = iFoodCollectionMapper.deleteById(id);
        return new RestBean(200,"正常",i);
    }


    /*
     *
     * 更新操作
     * */
    @PutMapping("/updateCollection")
    public RestBean<User> updateFoodLocalById(@RequestBody FoodCollection foodCollection){
        int i = iFoodCollectionMapper.updateById(foodCollection);
        return new RestBean(200,"正常",i);
    }
}

