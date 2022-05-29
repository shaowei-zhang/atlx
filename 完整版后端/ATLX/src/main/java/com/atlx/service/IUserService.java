package com.atlx.service;

import com.atlx.pojo.User;

import java.util.List;

public interface IUserService {
    //查询所有的用户信息
    List<User> findAll();

    //根据账户名查询用户
    List<User> findUserByAccount(String account);

    //添加用户
    boolean insertUser(User user);

    //更新用户
    boolean updateUser(User user);

    //注册
    boolean register(User user);

    //根据id删除用户
    boolean deleteUserByAccount(String account);

    //重置密码
    boolean resetPassword(User user);

    //上传头像
    boolean uploadAvatar(User user);

    //修改密码
    boolean updatePwd(User user);
}
