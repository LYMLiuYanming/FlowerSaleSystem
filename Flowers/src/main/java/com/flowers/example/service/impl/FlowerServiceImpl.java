package com.flowers.example.service.impl;

import com.flowers.example.mapper.FlowerMapper;
import com.flowers.example.pojo.Flower;
import com.flowers.example.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerMapper flowerMapper;
    @Override
    public List<Flower> searchAllFlower(){
        return flowerMapper.searchAllFlower();
    }
    @Override
    public List<Flower> searchFlower(Integer goodsid){
        return flowerMapper.searchFlower(goodsid);
    }
    @Override
    public List<Flower> searchFlowerByName(String name){
        return flowerMapper.searchFlowerByName(name);
    }
    @Override
    public Flower searchFlowerBuy(Integer goodsid){
        return flowerMapper.searchFlowerBuy(goodsid);
    }
    @Override
    public List<Flower> searchFlowerBySeller(String seller){
        return flowerMapper.searchFlowerBySeller(seller);
    }
    @Override
    public void insertFlower(String name, String address, float price, String picture, String seller){
        flowerMapper.insertFlower(name,address,price,picture,seller);
    }
    @Override
    public void insertFlower2(String name, String address, float price, String seller){
        flowerMapper.insertFlower2(name,address,price,seller);
    }
    @Override
    public void deleteFlower(Integer goodsid){
        flowerMapper.deleteFlower(goodsid);
    }
    @Override
    public void changeFlower(String name, String address, float price, String seller, Integer goodsid){
        flowerMapper.changeFlower(name, address, price, seller, goodsid);
    }
    @Override
    public void changeFlowerPicture(String picture, Integer goodsid){
        flowerMapper.changeFlowerPicture(picture, goodsid);
    }
    @Override
    public Integer getNewGoodsid(){
        return flowerMapper.getNewGoodsid();
    }
    @Override
    public void updateValue(Integer goodsid){
        flowerMapper.updateValue(goodsid);
    }
    @Override
    public void updateSales(Integer sales, Integer goodsid){
        flowerMapper.updateSales(sales, goodsid);
    }
}
