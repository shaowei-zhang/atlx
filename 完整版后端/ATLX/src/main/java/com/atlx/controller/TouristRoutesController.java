package com.atlx.controller;

import com.atlx.pojo.TouristRoutes;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.ITouristRoutesService;
import com.atlx.utils.UpAndDownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/touristRoutes")
public class TouristRoutesController {


    @Autowired
    ITouristRoutesService touristRoutesService;
    @Autowired
    UpAndDownloadFile upAndDownloadFile;

    //展示所有路线
    @PostMapping("/selectList")
    public RestBean<TouristRoutes> selectList() {
        List<TouristRoutes> touristRoutesList=touristRoutesService.selectList();

        return new RestBean(200,"正常",touristRoutesList);

    }

    //搜索页面
    @PostMapping("/selectOne")
    public RestBean<TouristRoutes> selectOne(@RequestBody Map map) {

        String startName = map.get("startName").toString();
        List<TouristRoutes> touristRoutes=touristRoutesService.selectOne(startName);

        return new RestBean(200,"正常",touristRoutes);

    }
    //添加路线
    @PostMapping("/insert")
    public RestBean<Void> insert(@RequestBody TouristRoutes touristRoutes){

        try{

            touristRoutesService.insert(touristRoutes);
        }catch (Exception e){
            System.out.println(e);
        }


        return new RestBean(200,"正常",null);
    }
    //通过Id删除路线
    @PostMapping ("/deleteById/{dels}")
    public RestBean<Void> deleteById(@PathVariable("dels") Integer[] dels) {
        int i=0;
        for(;i<dels.length;i++){
            touristRoutesService.deleteById(dels[i]);
        }
        if(i==dels.length){
            return new RestBean(200,"删除成功",null);
        }
        else{
            return new RestBean(200,"删除失败",null);
        }

    }

    //修改路线
    @RequestMapping("/updateById")
    public RestBean<Void> updateById(@RequestBody Map maps){
        TouristRoutes touristRoutes=new TouristRoutes();
        touristRoutes.setId(Integer.parseInt(maps.get("id").toString()));
        touristRoutes.setTouristName(maps.get("touristName").toString());
        touristRoutes.setStartName(maps.get("startName").toString());
        touristRoutes.setEndName(maps.get("endName").toString());
        touristRoutes.setPassName(maps.get("passName").toString());
        touristRoutes.setDescription(maps.get("description").toString());
        touristRoutes.setAvatar(maps.get("avatar").toString());

        touristRoutesService.updateById(touristRoutes);
        return new RestBean(200,"正常",null);

    }
    //修改路线
    @RequestMapping("/selectById/{id}")
    public RestBean selectById(@PathVariable("id") Integer id){
        TouristRoutes touristRoutes=touristRoutesService.selectById(id);

        return new RestBean(200,"正常",touristRoutes);

    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String url = upAndDownloadFile.uploadFile(file,"touristRoutes");

        return url;
    }
    @GetMapping("/{originalFilename}")
    public void download(@PathVariable String originalFilename, HttpServletResponse response) throws Exception {
        upAndDownloadFile.downloadFile(originalFilename,response);
    }
    //经纬度获取
    @PostMapping("/selectLnglat")
    public RestBean selectLnglat(@RequestBody Map map) {
        String startName = map.get("startName").toString();
        List<TouristRoutes> touristRoutes=touristRoutesService.selectOne(startName);

        Map<String,Double> doubleDoubleMap=new HashMap<>();

        String lnglat1=touristRoutes.get(0).getLnglat1();
        String lnglat2=touristRoutes.get(0).getLnglat2();
        String lnglat3=touristRoutes.get(0).getLnglat3();
        String[] sourceStrArray1 = lnglat1.split(",");
        String[] sourceStrArray2= lnglat2.split(",");
        String[] sourceStrArray3= lnglat3.split(",");

        doubleDoubleMap.put("lng1",Double.parseDouble(sourceStrArray1[0]));
        doubleDoubleMap.put("lat1",Double.parseDouble(sourceStrArray1[1]));

        doubleDoubleMap.put("lng2",Double.parseDouble(sourceStrArray2[0]));
        doubleDoubleMap.put("lat2",Double.parseDouble(sourceStrArray2[1]));


        doubleDoubleMap.put("lng3",Double.parseDouble(sourceStrArray3[0]));
        doubleDoubleMap.put("lat3",Double.parseDouble(sourceStrArray3[1]));

        return new RestBean(200,"正常",doubleDoubleMap);

    }


}
