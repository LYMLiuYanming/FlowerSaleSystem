package com.flowers.example.service;

import com.flowers.example.pojo.Money;

public interface MoneySevice {
    Money searchMoney(Integer id);
    void insertMoney(Integer id, float money, String username, String seller);
    float getBalance(Integer id);
}
