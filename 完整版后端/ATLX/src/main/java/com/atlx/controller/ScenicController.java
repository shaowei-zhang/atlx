package com.atlx.controller;


import com.atlx.dao.IScenicMapper;
import com.atlx.pojo.Region;
import com.atlx.pojo.Scenic;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.impl.RegionService;
import com.atlx.service.impl.ScenicService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import com.atlx.utils.UpAndDownloadFile;

@RestController
@RequestMapping("/scenic")
public class ScenicController {
    @Resource
    ScenicService scenicService;
    @Resource
    RegionService regionService;
    @Autowired
    private IScenicMapper iScenicMapper;
    @Resource
    private UpAndDownloadFile upAndDownloadFile;

    //联表查询信息
    @GetMapping("/scenicList")
    public RestBean<Scenic> query(){
        List<Scenic> scenicList=scenicService.query();
        return new RestBean(200,"正常",scenicList);
    }
    //根据ID查询数据
    @RequestMapping("/queryScenicById/{id}")
    public RestBean<Scenic> queryScenicById(@PathVariable("id")int id){

        List<Scenic> list=iScenicMapper.queryById(id);

        return new RestBean(200,"正常",list);
    }
    //根据名称查询景区信息
    @PostMapping ("/queryScenicbyName")
    public RestBean<Scenic> queryScenicbyName(@RequestBody Map map){
        String scenicName = map.get("scenicName").toString();

        List<Scenic> scenicList = iScenicMapper.queryByName(scenicName);
        return new RestBean(200,"正常",scenicList);
    }


    //新增景区信息
    @PostMapping("/insertScenic")
    public RestBean<Void> insertScenic(@RequestBody Map content){

        String regionName = (String)content.get("regionName");
        String scenicName = (String)content.get("scenicName");
        String scenicImg = (String)content.get("scenicImg");
        String scenicIntroduce = (String)content.get("scenicIntroduce");
        String price=content.get("price").toString();

        Region region = regionService.queryRegionByName(regionName);

        Scenic scenic = new Scenic();
        scenic.setPrice(price);
        scenic.setScenicImg(scenicImg);
        scenic.setRegionId(region.getId());
        scenic.setScenicName(scenicName);
        scenic.setScenicIntroduce(scenicIntroduce);

        scenicService.insertScenic(scenic);
        return new RestBean(200,"正常",null);
    }

    //修改景区信息
    @PutMapping("/updateScenic")
    public RestBean<Scenic> updateScenic(@RequestBody Scenic scenic){

        return new RestBean(200,"正常",iScenicMapper.updateById(scenic));
    }

    //删除景区信息
    @PostMapping("/deleteScenic/{dels}")
    public RestBean<Scenic> deleteScenic(@PathVariable Integer[] dels){
        for(int i=0;i<dels.length;i++){
            iScenicMapper.deleteById(dels[i]);
        }

        return new RestBean(200,"正常",null);
    }
    /**
     * 图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/scenicImg")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String url=upAndDownloadFile.uploadFile(file,"scenic");
        return url;
    }
    @GetMapping("/{originalFilename}")
    public void download(@PathVariable String originalFilename, HttpServletResponse response) throws IOException {
        upAndDownloadFile.downloadFile(originalFilename,response);
    }





}
