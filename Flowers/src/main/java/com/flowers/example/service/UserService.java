package com.flowers.example.service;

import com.flowers.example.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password, String email, Integer role);

    void resetPassword(String username, String newPass, String email);

    User findRAddressByUserName(String username);

    void updateUser(String username, String password, String email, String birthday, String raddress, String sex, Integer id, String payWithPassWord);

    void updateBalance(String username, float balance);
    void insertBalance(float balance, String username);
    float getBalance(String username);
}
