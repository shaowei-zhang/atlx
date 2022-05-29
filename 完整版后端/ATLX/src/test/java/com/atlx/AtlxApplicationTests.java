package com.atlx;


import com.atlx.dao.IFoodMessageMapper;
import com.atlx.pojo.*;
import com.atlx.service.*;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootTest
class AtlxApplicationTests {

    @Autowired
    IFoodMessageMapper iFoodMessageMapper;
    @Autowired
    IFoodOrderService iFoodOrderService;
    @Autowired
    IMenuService iMenuService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IPermissionService iPermissionService;


    @Test
    void contextLoads() {

        List<User> user = iUserService.findUserByAccount("lisi");
        Role role = iRoleService.queryRoleById(user.get(0).getRoleId());
        role.setPermissions(iPermissionService.queryPermissionByRole(role));

        List<String> list = new ArrayList<>();
        for(int i=0;i<role.getPermissions().size();i++){
            list.add(role.getPermissions().get(i).getUrl());
        }
        System.out.println("权限========================》》》"+list);
    }

    @Test
    void Test1(){

        String javaString = "ABb5555554444KK,mmmmmbbbcN";

        System.out.println(javaString.toUpperCase());


    }


    @Test
    void Test2(){
        List<SysFatherMenu> sysFatherMenus = iMenuService.findFatherMenu();
        for (int i=0;i<sysFatherMenus.size();i++){
            List<SysChildrenMenu> sysChildrenMenus = iMenuService.findChildrenMenuByFatherMenuId(sysFatherMenus.get(i).getId());
            sysFatherMenus.get(i).setChildren(sysChildrenMenus);
        }
        sysFatherMenus.forEach(System.out::println);
    }

}
