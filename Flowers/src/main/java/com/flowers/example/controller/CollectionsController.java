package com.flowers.example.controller;

import com.flowers.example.pojo.Cart;
import com.flowers.example.pojo.Collections;
import com.flowers.example.pojo.Flower;
import com.flowers.example.pojo.User;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CollectionsController {
    @Autowired
    private CollectionsService collectionService;
    @Autowired
    private FlowerService flowerService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MoneySevice moneySevice;
    @PostMapping("/insertCollection")
    public Result insertCollection(@RequestBody Collections collections){
        Integer goodsid = collections.getGoodsid();
        String username = collections.getUsername();
        Flower flower = flowerService.searchFlowerBuy(goodsid);
        String name = flower.getName();
        String address = flower.getAddress();
        float price = flower.getPrice();
        String picture = flower.getPicture();
        User user = userService.findRAddressByUserName(username);
        String raddress = user.getRaddress();
        Collections collections1 = collectionService.findCollectionByGoodsid(goodsid);
        if (collections1 != null){
            return ResultFactory.buildFailResult("已存在");
        } else {
            collectionService.insertCollection(name, address, price, goodsid, picture, username, raddress);
            return ResultFactory.buildSuccessResult(200);
        }
    }
    @PostMapping("searchAllCollectionsByUsername")
    public Result searchAllCollectionsByUsername(@RequestBody Collections collections){
        String username = collections.getUsername();
        Object CollectionsList = collectionService.searchAllCollectionsByUsername(username);
        return ResultFactory.buildSuccessResult(CollectionsList);
    }
    @PostMapping("buyFlowerFromCollections")
    public Result buyFlowerFromCollections(@RequestBody Cart cart){
        String username = cart.getUsername();
        Integer goodsid = cart.getGoodsid();
        String raddress = cart.getRaddress();
        Integer amount = cart.getAmount();
        Flower flower = flowerService.searchFlowerBuy(goodsid);
        String name = flower.getName();
        float price = flower.getPrice();
        float totalprice = amount * price;
        String address = flower.getAddress();
        String seller = flower.getSeller();
        User u = userService.findByUserName(username);
        float balance = u.getBalance();
        if (totalprice>balance){
            return ResultFactory.buildSuccessResult(400);
        }else {
            float balance_after = balance-totalprice;
            userService.updateBalance(username,balance_after);
            collectionService.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller);
            Integer id =  orderService.getNewOrderUnid();
            moneySevice.insertMoney(id,totalprice,username,seller);
            return ResultFactory.buildSuccessResult(200);
        }
    }
    @PostMapping("/cancelCollection")
    public Result cancelCollection(@RequestBody Collections collections){
        Integer goodsid = collections.getGoodsid();
        collectionService.cancelCollection(goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
}
