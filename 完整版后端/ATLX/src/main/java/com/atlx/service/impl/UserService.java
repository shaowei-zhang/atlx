package com.atlx.service.impl;

import com.atlx.pojo.User;
import com.atlx.dao.IUserMapper;
import com.atlx.service.IUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserMapper iUserMapper;

    @Override
    public List<User> findAll() {
        return iUserMapper.findAll();
    }

    @Override
    public List<User> findUserByAccount(String account) {
        return iUserMapper.findUserByAccount(account);
    }

    @Override
    public boolean insertUser(User user) {
        return iUserMapper.insertUser(user)>0;
    }

    @Override
    public boolean updateUser(User user) {
        return iUserMapper.updateUser(user)>0;
    }

    @Override
    public boolean register(User user) {

        String salt = UUID.randomUUID().toString();
        String password = new Sha256Hash(user.getPassword(),salt,1024).toBase64();
        user.setPassword(password);
        user.setSalt(salt);

        return iUserMapper.register(user)>0;
    }

    @Override
    public boolean deleteUserByAccount(String account) {
        return iUserMapper.deleteUserByAccount(account)>0;
    }

    @Override
    public boolean resetPassword(User user) {
        String salt = UUID.randomUUID().toString();
        String password = new Sha256Hash(user.getPassword(),salt,1024).toBase64();
        user.setPassword(password);
        user.setSalt(salt);
        return iUserMapper.resetPassword(user)>0;
    }

    @Override
    public boolean uploadAvatar(User user) {
        return iUserMapper.uploadAvatar(user)>0;
    }

    @Override
    public boolean updatePwd(User user){

        return iUserMapper.updatePwd(user)>0;
    }
}
