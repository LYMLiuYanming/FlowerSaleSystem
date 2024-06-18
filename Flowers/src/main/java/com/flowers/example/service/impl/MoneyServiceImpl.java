package com.flowers.example.service.impl;

import com.flowers.example.mapper.MoneyMapper;
import com.flowers.example.pojo.Money;
import com.flowers.example.service.MoneySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImpl implements MoneySevice {
    @Autowired
    private MoneyMapper moneyMapper;
    @Override
    public void insertMoney(Integer id, float money, String username, String seller){
        moneyMapper.insertMoney(id, money, username, seller);
    }
    @Override
    public Money searchMoney(Integer id){
        return moneyMapper.searchMoney(id);
    }
    @Override
    public float getBalance(Integer id){
        return moneyMapper.getBalance(id);
    }
}
