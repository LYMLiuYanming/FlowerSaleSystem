package com.flowers.example.service;

import com.flowers.example.pojo.Flower;

import java.util.List;

public interface FlowerService {
    List<Flower> searchAllFlower();
    List<Flower> searchFlower(Integer goodsid);
    Flower searchFlowerBuy(Integer goodsid);
    List<Flower> searchFlowerByName(String name);
    List<Flower> searchFlowerBySeller(String seller);
    void insertFlower(String name, String address, float price, String picture, String seller);
    void insertFlower2(String name, String address, float price, String seller);
    void deleteFlower(Integer goodsid);
    void changeFlower(String name, String address, float price, String seller, Integer goodsid);
    void changeFlowerPicture(String picture, Integer goodsid);
    Integer getNewGoodsid();
    void updateValue(Integer goodsid);
    void updateSales(Integer sales, Integer goodsid);
}
