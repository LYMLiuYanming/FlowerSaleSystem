package com.flowers.example.controller;


import cn.hutool.core.io.FileUtil;
import com.flowers.example.pojo.Cart;
import com.flowers.example.pojo.Collections;
import com.flowers.example.pojo.Flower;
import com.flowers.example.pojo.User;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.flowers.example.utils.*;

@RestController
@RequestMapping("/api")
public class FlowerController {
    String E = "error";
    @Autowired
    private FlowerService flowerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private MoneySevice moneySevice;
    @Autowired
    private CollectionsService collectionsService;
    @Autowired
    private CartService cartService;
    @PostMapping("/getAllFlower")
    public Result getAllFlower(){
        Object flowerList = flowerService.searchAllFlower();
        return ResultFactory.buildSuccessResult(flowerList);
    }
    @PostMapping("/searchFlower")
    public Result searchFlower(@RequestBody Flower flower){
        Integer goodsid = flower.getGoodsid();
        Object flowerMessageList = flowerService.searchFlower(goodsid);
        return ResultFactory.buildSuccessResult(flowerMessageList);
    }
    @PostMapping("/searchFlowerByName")
    public Result searchFlowerByName(@RequestBody Flower flower){
        String name = flower.getName();
        name = "%"+name+"%";
        Object flowerList = flowerService.searchFlowerByName(name);
        return ResultFactory.buildSuccessResult(flowerList);
    }
    @PostMapping("/buyFlower")
    public Result buyFlower(@RequestBody Flower flower){
        Integer goodsid = flower.getGoodsid();
        Integer number = flower.getNumber();
        String username = flower.getUsername();
        Flower flower1 = flowerService.searchFlowerBuy(goodsid);
        String name = flower1.getName();
        String address = flower1.getAddress();
        String seller = flower1.getSeller();
        float price = flower1.getPrice();
        float totalprice = price * number;
        User u = userService.findRAddressByUserName(username);
        String raddress = u.getRaddress();
        User user = userService.findByUserName(username);
        float balance = user.getBalance();
        if (totalprice>balance){
            return ResultFactory.buildSuccessResult(400);
        }else {
            orderService.submitOrder(name,address,goodsid,number,username,raddress,price,totalprice,seller);
            Integer id =  orderService.getNewOrderUnid();
            moneySevice.insertMoney(id,totalprice,username,seller);
            float balance_after = balance-totalprice;
            userService.updateBalance(username,balance_after);
            return ResultFactory.buildSuccessResult(200);
        }
    }
    @PostMapping("/getFlowerBySeller")
    public Result getFlowerBySeller(@RequestBody Flower flower){
        String seller = flower.getSeller();
        Object FlowerList = flowerService.searchFlowerBySeller(seller);
        return ResultFactory.buildSuccessResult(FlowerList);
    }
    @PostMapping("/uploadGoodsPicture")
    public Result upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam Integer goodsid){
        System.out.println("multipartFile = " + multipartFile);
        String GoodsPictureLoad = uploadGoodsPictureUtil.upload(multipartFile).replaceAll("\\\\", "/");
        System.out.println(GoodsPictureLoad);
        String picture = FileUtil.getName(GoodsPictureLoad);
        flowerService.changeFlowerPicture(picture,goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/changeFlower")
    public Result changeFlower(@RequestBody Flower flower){
        Integer goodsid = flower.getGoodsid();
        String seller = flower.getSeller();
        String name = flower.getName();
        String address = flower.getAddress();
        float price = flower.getPrice();
        Collections collections = collectionsService.findCollectionByGoodsid(goodsid);
        Cart cart = cartService.findCartByGoodsid(goodsid);
        if (collections!=null){
            collectionsService.updateCollections(name,price,address,goodsid);
        }
        if (cart!=null){
            Integer amount = cart.getAmount();
            float totalprice = price*amount;
            cartService.updateCart(name,price,totalprice,address,goodsid);
        }
        flowerService.changeFlower(name,address,price,seller,goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/deleteFlower")
    public Result deleteFlower(@RequestBody Flower flower){
        Integer goodsid = flower.getGoodsid();
        flowerService.deleteFlower(goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/insertFlower")
    public Result insertFlower(@RequestBody Flower flower){
        String name = flower.getName();
        String address = flower.getAddress();
        float price = flower.getPrice();
        String seller = flower.getSeller();
        flowerService.insertFlower2(name,address,price,seller);
        return ResultFactory.buildSuccessResult(200);
    }
}
