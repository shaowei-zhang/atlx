package com.atlx.controller;

import com.atlx.dao.IFoodCollectionMapper;
import com.atlx.dao.ScenicCollectionMapper;
import com.atlx.pojo.*;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IScenicService;
import com.atlx.service.impl.ScenicCollectionService;
import net.sf.jsqlparser.parser.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.jsqlparser.parser.feature.Feature.insert;

/**

 */
@RequestMapping("/scenicCollection")
@RestController
public class ScenicCollectionController {

    @Autowired
    private IScenicService iScenicService;
    @Autowired
    private ScenicCollectionMapper scenicCollectionMapper;
    @Autowired
    private ScenicCollectionService scenicCollectionService;

    //仅插入FoodCollection表
    @PostMapping("/collectionScenic")
    public RestBean<User> insertFoodLocal(@RequestBody Map map){
        String scenicName = map.get("scenicName").toString();
        Scenic scenic = iScenicService.queryIdByName(scenicName);
        ScenicCollection scenicCollection = new ScenicCollection();
        scenicCollection.setScenicId(scenic.getId());
        scenicCollection.setAccount(map.get("account").toString());
        int insert = scenicCollectionMapper.insert(scenicCollection);

        return new RestBean(200,"正常",insert);
    }




    //联表查询 foodCollection FoodCollection
    @PostMapping("/queryAllMessage")
    public RestBean<User> queryAllMessage(@RequestBody Map map){
        List<ScenicCollection> scenicCollections = scenicCollectionService.queryAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",scenicCollections);
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
           m= scenicCollectionMapper.deleteById(dels[i]);
        }
        return new RestBean(200,"正常",m);
    }



}


