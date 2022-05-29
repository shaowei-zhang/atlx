package com.atlx.controller;

import com.atlx.dao.IHotelMapper;
import com.atlx.pojo.Hotel;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.impl.HotelService;
import com.atlx.utils.UpAndDownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;
    @Autowired
    private IHotelMapper iHotelMapper;
    @Resource
    private UpAndDownloadFile upAndDownloadFile;

    /**
     * 根据id查询
     * @param editId
     * @return
     */
    @GetMapping("/queryHotelById/{editId}")
    public RestBean<Hotel> query(@PathVariable Integer editId){
            Hotel hotel = iHotelMapper.selectById(editId);
            return new RestBean(200,"正常",hotel);
    }

    /**
     * 根据名称查询
     * @param
     * @return
     */
    @PostMapping("/queryByMap")
    public RestBean<Hotel> query(@RequestBody Map map){

        String hotelName = map.get("hotelName").toString();

        List<Hotel> hotels = hotelService.queryByName(hotelName);
        return new RestBean(200,"正常",hotels);
    }



    @GetMapping("/hotelList")
    public RestBean<Hotel> queryHotel(){
        System.out.println("success");
        List<Hotel> hotelList=hotelService.query();
        hotelList.forEach(System.out::println);
        return new RestBean(200,"正常",hotelList);
    }

    @PostMapping("/addHotel")
    public RestBean<Hotel> addHotel(@RequestBody Hotel hotel){
        boolean b = hotelService.addHotel(hotel);
        return new RestBean(200,"正常",b);
    }


    /*
    *
    * 更新
    *
    * */
    @PutMapping("/updateHotel")
    public RestBean<Hotel> updateHotel(@RequestBody Hotel hotel){
        int i = iHotelMapper.updateById(hotel);
        return new RestBean(200,"正常",i);
    }

    /**
     * 删除
     * @param dels
     * @return
     */
    @PostMapping("/delHotel/{dels}")
    public RestBean<Void> delHotel(@PathVariable("dels") Integer[] dels){
        //int i = iHotelMapper.deleteById(del);
        for (int i=0;i<dels.length;i++){
            iHotelMapper.deleteById(dels[i]);
        }
        return new RestBean(200,"正常",null);
    }

    /**
     * 图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/hotelImg")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String url=upAndDownloadFile.uploadFile(file,"hotel");
        return url;
    }

    //String type = FileUtil.extName(originalFilename);
    // 定义一个文件唯一的标识码
    //String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

    @GetMapping("/{originalFilename}")
    public void download(@PathVariable String originalFilename, HttpServletResponse response) throws IOException {
        upAndDownloadFile.downloadFile(originalFilename,response);
    }




}
