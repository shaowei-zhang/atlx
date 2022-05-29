package com.atlx.controller;

import com.atlx.dao.IFoodLocalMapper;
import com.atlx.dao.IRegionMapper;
import com.atlx.pojo.FoodLocal;
import com.atlx.pojo.Region;
import com.atlx.pojo.User;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IFoodLocalService;
import com.atlx.service.IRegionService;
import com.atlx.utils.UpAndDownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshaowei
 * @date 2022/5/9 14:28
 * @description
 */
@RestController
@RequestMapping("/food")
public class FoodLocalController {

    @Autowired
    private IFoodLocalService iFoodLocalService;
    @Autowired
    private IFoodLocalMapper iFoodLocalMapper;
    @Autowired
    private IRegionMapper iRegionMapper;
    @Autowired
    UpAndDownloadFile upAndDownloadFile;
    @Autowired
    private IRegionService iRegionService;

    /*
    * //联表查询， 查询food_local，region表中的全部信息
    * 分页展示
    *
    * */
    @GetMapping("/queryAllMessage/{pageSize}/{pageCode}")
    public RestBean<User> queryAllMessage(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageCode") Integer pageCode){
        List<FoodLocal> allFoodMessage = iFoodLocalService.queryAllMessage(pageSize,pageCode);
        return new RestBean(200,"正常",allFoodMessage);
    }
    /*
    *
    * 联表查询美食信息
    * 不分页
    * */
    @GetMapping("/queryAllFoodInfo")
    public RestBean<FoodLocal> queryFoodLocal(){
        List<FoodLocal> foodLocals = iFoodLocalService.queryAllFoodInfo();
        return  new RestBean(200,"正常",foodLocals);
    }





    /*
    * 查询全部的foodlocal的信息
    * 获取长度，length
    * */
    @GetMapping("/queryFoodLocalLength")
    public RestBean<FoodLocal>  Length(){
        List<FoodLocal> foodLocals = iFoodLocalMapper.selectList(null);
        return  new RestBean(200,"正常",foodLocals);
    }


    //根据id 查询
    @GetMapping("/queryById/{editId}")
    public RestBean<User> queryById(@PathVariable Integer editId){
        FoodLocal foodLocal = iFoodLocalMapper.selectById(editId);
        return new RestBean(200,"正常",foodLocal);
    }
    //通过条件查询之一(selectByMap)
    /*
    * 联表根据美食名查询
    * */
    @PostMapping("/queryByMap")
    public RestBean<User> queryByMap(@RequestBody Map map){
        String foodName = map.get("foodName").toString();
        List<FoodLocal> foodLocals = iFoodLocalService.queryByName(foodName);
        return new RestBean(200,"正常",foodLocals);
    }



    @PostMapping("/uploadFoodImg")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String foodImgUrl= upAndDownloadFile.uploadFile(file,"food");
        return foodImgUrl;
    }

    //仅插入 foodlocal表
    @PostMapping("/insertFoodLocal")
    public RestBean<User> insertFoodLocal(@RequestBody Map map){
        String regionName = (String)map.get("regionName");
        String foodImg=(String)map.get("foodImg");
        String foodName=(String)map.get("foodName");
        String foodClass=(String)map.get("foodClass");
        String foodIntroduce=(String)map.get("foodIntroduce");
//        int price=Integer.parseInt( map.get("price").toString());
        String price=map.get("price").toString();

//        System.out.println("===========>>"+price);
        Region region = iRegionService.queryName(regionName);
        FoodLocal foodLocal = new FoodLocal();

        foodLocal.setPrice(price);
        foodLocal.setRegionId(region.getId());
        foodLocal.setFoodImg(foodImg);
        foodLocal.setFoodName(foodName);
        foodLocal.setFoodClass(foodClass);
        foodLocal.setFoodIntroduce(foodIntroduce);


        int insert = iFoodLocalMapper.insert(foodLocal);

        return new RestBean(200,"正常",insert);
    }

    /*
    *
    * 删除操作
    *
    * */
    @DeleteMapping("/deleteFoodLocalById/{dels}")
    public RestBean<User> deleteFoodLocalById(@PathVariable Integer[] dels){
        int m=0;
        for (int i = 0; i < dels.length; i++) {
          m= iFoodLocalMapper.deleteById(dels[i]);
        }
//        int i = iFoodLocalMapper.deleteById(dels);
        return new RestBean(200,"正常",m);
    }

    /*
    *
    * 更新操作
    * */
    @PutMapping("/updateFoodLocal")
    public RestBean<User> updateFoodLocalById(@RequestBody Map map){
        String regionName = (String)map.get("regionName");

        Integer id=(Integer) map.get("id");
        String foodImg=(String)map.get("foodImg");
        String foodName=(String)map.get("foodName");
        String foodClass=(String)map.get("foodClass");
        String foodIntroduce=(String)map.get("foodIntroduce");
        String price=map.get("price").toString();

        Region region = iRegionService.queryName(regionName);
        FoodLocal foodLocal = new FoodLocal();

        foodLocal.setId(id);
        foodLocal.setRegionId(region.getId());
        foodLocal.setFoodImg(foodImg);
        foodLocal.setPrice(price);
        foodLocal.setFoodName(foodName);
        foodLocal.setFoodClass(foodClass);
        foodLocal.setFoodIntroduce(foodIntroduce);


        int i = iFoodLocalMapper.updateById(foodLocal);
        return new RestBean(200,"正常",i);
    }
    @GetMapping("/{originalFilename}")
    public void download(@PathVariable String originalFilename, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件u\
        upAndDownloadFile.downloadFile(originalFilename,response);
    }

}
