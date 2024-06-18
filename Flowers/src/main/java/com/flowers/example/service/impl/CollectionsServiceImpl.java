package com.flowers.example.service.impl;

import com.flowers.example.mapper.CollectionsMapper;
import com.flowers.example.pojo.Collections;
import com.flowers.example.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionsServiceImpl implements CollectionsService {
    @Autowired
    private CollectionsMapper collectionMapper;
    @Override
    public void insertCollection(String name, String address, float price, Integer goodsid, String picture, String username, String raddress){
        collectionMapper.insertCollection(name, address, price, goodsid, picture, username, raddress);
    }
    @Override
    public Collections findCollectionByGoodsid(Integer goodsid){
        return collectionMapper.findCollectionByGoodsid(goodsid);
    }
    @Override
    public List<Collections> searchAllCollectionsByUsername(String username){
        return collectionMapper.searchAllCollectionsByUsername(username);
    }
    @Override
    public void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller){
        collectionMapper.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller);
    }
    @Override
    public void cancelCollection(Integer goodsid){
        collectionMapper.cancelCollection(goodsid);
    }
    @Override
    public void updateCollections(String name, float price, String address, Integer goodsid){
        collectionMapper.updateCollections(name, price, address, goodsid);
    }
}
