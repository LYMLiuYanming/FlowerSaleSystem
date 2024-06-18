package com.flowers.example.service;

import com.flowers.example.pojo.Collections;

import java.util.List;

public interface CollectionsService {
    void insertCollection(String name, String address, float price, Integer goodsid, String picture, String username, String raddress);
    Collections findCollectionByGoodsid(Integer goodsid);
    List<Collections> searchAllCollectionsByUsername(String username);
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller);
    void cancelCollection(Integer goodsid);
    void updateCollections(String name, float price, String address, Integer goodsid);
}
