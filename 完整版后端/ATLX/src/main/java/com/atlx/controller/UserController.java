package com.atlx.controller;

import com.atlx.pojo.HotelOrder;
import com.atlx.pojo.Role;
import com.atlx.pojo.ScenicOrder;
import com.atlx.pojo.User;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IRoleService;
import com.atlx.service.IUserService;
import com.atlx.utils.UpAndDownloadFile;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    UpAndDownloadFile upAndDownloadFile;

    @PostMapping("/queryUser")
    public RestBean queryUser(@RequestBody Map searchAccount) {

        String account = searchAccount.get("account").toString();
        List<User> userList=iUserService.findAll();

        for(int i=0;i<userList.size();i++){
            if (userList.get(i).getAccount().equals(account)){
                List<User> users= iUserService.findUserByAccount(account);
                return new RestBean(200,"请求成功",users);
            }
        }


        return new RestBean(200,"正常",userList);
    }

    @RequestMapping("/editUserData")
    public RestBean editUserData(@RequestBody Map editAccount) {

        List<User> userList=iUserService.findUserByAccount(editAccount.get("account").toString());

        Role role = iRoleService.queryRoleById(userList.get(0).getRoleId());
        userList.get(0).setRole(role);

        return new RestBean(200,"正常",userList.get(0));
    }

    @PostMapping("/insertUser")
    public RestBean<Void> insertUser(@RequestBody Map userInsertInfo) {

        User user=new User();
        user.setUsername(userInsertInfo.get("username").toString());
        user.setAccount(userInsertInfo.get("account").toString());
        user.setEmail(userInsertInfo.get("email").toString());
        user.setPassword(userInsertInfo.get("password").toString());
        user.setAvatar(userInsertInfo.get("avatar").toString());
        Role role =iRoleService.queryRoleByRoleName(userInsertInfo.get("role").toString());
        user.setRoleId(role.getId());

        try{
            iUserService.insertUser(user);
            return new RestBean(200,"添加成功",null);
        }catch (Exception e){
            return new RestBean(500,"用户已经存在",null);
        }




    }
    //编辑后
    @PostMapping("/updateUser")
    public RestBean<Void> updateUser(@RequestBody Map userInsertInfo) {
        User user= new User();
        user.setUsername(userInsertInfo.get("username").toString());
        user.setAccount(userInsertInfo.get("account").toString());
        user.setEmail(userInsertInfo.get("email").toString());
        user.setAvatar(userInsertInfo.get("avatar").toString());

        Role role =iRoleService.queryRoleByRoleName(userInsertInfo.get("role").toString());
        user.setRoleId(role.getId());

        List<User> user1=iUserService.findUserByAccount(userInsertInfo.get("account").toString());
        Integer id=user1.get(0).getId();
        user.setId(id);
        iUserService.updateUser(user);

        return new RestBean(200,"查询用户列表",null);
    }

    @RequestMapping("/deleteUser")
    public RestBean<Void> deleteUser(@RequestBody Map deletes) {
        List<String> dels = (List<String>)deletes.get("dels");
        for(int i=0;i<dels.size();i++){
            iUserService.deleteUserByAccount(dels.get(i));
        }

        return new RestBean(200,"删除成功",null);
    }

    @PostMapping("/userInfo")
    public RestBean userInfo(@RequestBody Map userAccount){
        String account = userAccount.get("account").toString();

        List<User> user = iUserService.findUserByAccount(account);

        return new RestBean(200,"注册成功",user.get(0));
    }

    @PostMapping("/avatar")
    public RestBean uploadAvatar(@RequestBody User user){

        if(iUserService.uploadAvatar(user)){
            return new RestBean(200,"上传头像成功",null);
        }

        return new RestBean(500,"上传头像失败",null);
    }

    @PostMapping("/uploadAvatar")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String url = upAndDownloadFile.uploadFile(file,"user");
            return url;
    }

    @GetMapping("/{originalFilename}")
    public void download(@PathVariable String originalFilename, HttpServletResponse response) throws IOException {
      upAndDownloadFile.downloadFile(originalFilename,response);
    }

    //修改密码
    @RequestMapping("/updatePwd")
    public RestBean<Void> updatePwd(@RequestBody Map maps) {
        List<User> user=iUserService.findUserByAccount(maps.get("account").toString());

        String olderPass=maps.get("password").toString();
        String password0 = new Sha256Hash(olderPass,user.get(0).getSalt(),1024).toBase64();
        if(password0.equals(user.get(0).getPassword())){
            String pass=maps.get("pass").toString();
            String salt = UUID.randomUUID().toString();
            String password = new Sha256Hash(pass,salt,1024).toBase64();
            user.get(0).setPassword(password);
            user.get(0).setSalt(salt);
            iUserService.updatePwd(user.get(0));

            return new RestBean(200,"修改成功",null);
        }
        return new RestBean(500,"原密码错误",null);
    }
}
