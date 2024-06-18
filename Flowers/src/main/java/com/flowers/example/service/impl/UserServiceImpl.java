package com.flowers.example.service.impl;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.flowers.example.mapper.UserMapper;
import com.flowers.example.pojo.User;
import com.flowers.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password, String email, Integer role) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(password);
        userMapper.add(username,password,email,role);
    }

    @Override
    public void resetPassword(String username, String newPass, String email){
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(newPass);
        userMapper.resetPassword(username, newPass, email);
    }

    @Override
    public User findRAddressByUserName(String username){
        return userMapper.findRAddressByUserName(username);
    }
    @Override
    public void updateUser(String username, String password, String email, String birthday, String raddress, String sex, Integer id, String payWithPassWord){
        userMapper.updateUser(username,password,email,birthday,raddress,sex,id,payWithPassWord);
    }
    @Override
    public void updateBalance(String username, float balance){
        userMapper.updateBalance(username,balance);
    }
    @Override
    public void insertBalance(float balance, String username){
        userMapper.insertBalance(balance,username);
    }
    @Override
    public float getBalance(String username){
        return userMapper.getBalance(username);
    }
}
