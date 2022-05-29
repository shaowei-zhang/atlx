package com.atlx.dao;

import com.atlx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMapper  {
    //查询所有的用户信息
    List<User> findAll();

    //根据账户名查询用户
    List<User> findUserByAccount(String account);

    //添加用户
    int insertUser(User user);

    //更新用户
    int updateUser(User user);


    //注册
    int register(User user);

    //根据id删除用户
    int deleteUserByAccount(String account);

    //重置密码
    int resetPassword(User user);

    //上传头像
    int uploadAvatar(User user);

    //修改密码

    int updatePwd(User user);
}
