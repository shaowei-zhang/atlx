package com.atlx.controller;

import com.atlx.dao.IScenicMessageMapper;
import com.atlx.pojo.Scenic;
import com.atlx.pojo.ScenicMessage;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IScenicMessageService;
import com.atlx.service.IScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scenicMessage")
public class ScenicMessageController {
    @Autowired
    private IScenicMessageMapper iScenicMessageMapper;
    @Autowired
    private IScenicMessageService iScenicMessageService;
    @Autowired
    IScenicService iScenicService;

    @PostMapping("/findAllByName")
    public RestBean<ScenicMessage> findAllByName(@RequestBody Map map){
        String scenicName = map.get("scenicName").toString();
        List<ScenicMessage> scenicMessages = iScenicMessageMapper.findAllByName(scenicName);
        return new RestBean(200,"正常",scenicMessages);
    }

    @PostMapping("/findAllByAccount")
    public RestBean<ScenicMessage> findAllByAccount(@RequestBody Map map){
        List<ScenicMessage> zhangsan = iScenicMessageService.findAllByAccount(map.get("account").toString());
        return new RestBean(200,"正常",zhangsan);
    }

    @PostMapping("/insertScenicMessage")
    public RestBean<ScenicMessage> insertScenicLocal(@RequestBody Map message){
        ScenicMessage scenicMessage = new ScenicMessage();
        String scenicName = message.get("scenicName").toString();

        Scenic scenic = iScenicService.queryIdByName(scenicName);

        scenicMessage.setScenicId(scenic.getId());
        scenicMessage.setAccount(message.get("account").toString());
        scenicMessage.setCommentDate(new Date());
        scenicMessage.setComment(message.get("comment").toString());
        int insert = iScenicMessageMapper.insert(scenicMessage);
        return new RestBean(200,"正常",insert);
    }


    @DeleteMapping("/deleteScenicMessageById/{dels}")
    public RestBean<ScenicMessage> deleteScenicLocalById(@PathVariable Integer[] dels){

        int m=0;
        for (int i = 0; i < dels.length; i++) {
            m =  iScenicMessageMapper.deleteById(dels[i]);
        }

        return new RestBean(200,"正常",m);

    }
}
