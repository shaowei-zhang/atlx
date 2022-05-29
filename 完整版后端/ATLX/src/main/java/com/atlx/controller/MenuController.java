package com.atlx.controller;

import com.atlx.pojo.*;
import com.atlx.pojo.resp.RestBean;
import com.atlx.service.IMenuService;
import com.atlx.service.IPermissionService;
import com.atlx.service.IRoleService;
import com.atlx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    IMenuService iMenuService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IPermissionService iPermissionService;

    @PostMapping("/adminMenu")
    public RestBean findMenu(@RequestBody Map userAccount){
//        String account = userAccount.get("account").toString();

        List<SysFatherMenu> sysFatherMenus = iMenuService.findFatherMenu();

        for (int i=0;i<sysFatherMenus.size();i++){
            List<SysChildrenMenu> sysChildrenMenus = iMenuService.findChildrenMenuByFatherMenuId(sysFatherMenus.get(i).getId());
            sysFatherMenus.get(i).setChildren(sysChildrenMenus);
        }


        return new RestBean(200,"表单列表",sysFatherMenus);
    }
    @PostMapping("/userPermission")
    public RestBean userPermission(@RequestBody Map userAccount){

        String account = userAccount.get("account").toString();

        List<User> user = iUserService.findUserByAccount(account);
        Role role = iRoleService.queryRoleById(user.get(0).getRoleId());
        role.setPermissions(iPermissionService.queryPermissionByRole(role));

        List<String> lists = new ArrayList<>();
        for(int i=0;i<role.getPermissions().size();i++){
            lists.add(role.getPermissions().get(i).getUrl());
        }

        return new RestBean(200,"权限列表",lists);
    }
}
