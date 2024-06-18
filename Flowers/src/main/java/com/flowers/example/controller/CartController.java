package com.flowers.example.controller;

import com.flowers.example.pojo.Cart;
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
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private FlowerService flowerService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MoneySevice moneySevice;
    @PostMapping("/insertCart")
    public Result insertCart(@RequestBody Cart cart){
        Integer goodsid = cart.getGoodsid();
        Integer amount = cart.getAmount();
        String username = cart.getUsername();
        Flower flower = flowerService.searchFlowerBuy(goodsid);
        String name = flower.getName();
        float price = flower.getPrice();
        String picture = flower.getPicture();
        String address = flower.getAddress();
        String seller = flower.getSeller();
        float totalprice = amount * price;
        Cart cart1 = cartService.findCartByGoodsid(goodsid);
        User user = userService.findRAddressByUserName(username);
        String raddress = user.getRaddress();
        if (cart1 != null){
            Cart cart2 = cartService.findAmountByGoodsid(goodsid);
            float amount_before = cart2.getAmount();
            float amount_after = amount_before + amount;
            float totalprice_after = amount_after * price;
            cartService.insertCartOnlyAmount(goodsid, amount_after, totalprice_after, raddress, seller);
            return ResultFactory.buildSuccessResult(200);
        }else {
            cartService.insertCart(name,price,totalprice,amount,picture,address,goodsid,username,raddress,seller);
            return ResultFactory.buildSuccessResult(200);
        }
    }
    @PostMapping("/getAllCart")
    public Result getAllCart(@RequestBody Cart cart){
        String username = cart.getUsername();
        Object cartList = cartService.seatchAllCartByUsername(username);
        return ResultFactory.buildSuccessResult(cartList);
    }
    @PostMapping("/deleteCartByGoodsid")
    public Result deleteCart(@RequestBody Cart cart){
        Integer goodsid = cart.getGoodsid();
        cartService.deleteCartByGoodsid(goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/getCartMessage")
    public Result getCartMessageByGoodsId(@RequestBody Cart cart){
        Integer goodsid = cart.getGoodsid();
        Cart cart1 = cartService.findCartByGoodsid(goodsid);
        return ResultFactory.buildSuccessResult(cart1);
    }
    @PostMapping("/changeCart")
    public Result changeCart(@RequestBody Cart cart){
        Integer goodsid = cart.getGoodsid();
        Integer amount = cart.getAmount();
        String raddress = cart.getRaddress();
        Cart cart1 = cartService.findCartByGoodsid(goodsid);
        float price = cart1.getPrice();
        String raddress_before = cart1.getRaddress();
        float totalprice = price * amount;
        if (raddress == null){
            cartService.changeCart(amount, raddress_before, totalprice, goodsid);
        }else {
            cartService.changeCart(amount, raddress, totalprice, goodsid);
        }
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/insertOrderFromCart")
    public Result insertOrderFromCart(@RequestBody Cart cart){
        Integer number = cart.getNumber();
        String username_ = cart.getUsername();
        Cart cart1 = cartService.findCartByNumber(number);
        String name = cart1.getName();
        float price = cart1.getPrice();
        float totalprice = cart1.getTotalprice();
        Integer amount = cart1.getAmount();
        Integer goodsid = cart1.getGoodsid();
        String address = cart1.getAddress();
        String username = cart1.getUsername();
        String raddress = cart1.getRaddress();
        String seller = cart1.getSeller();
        User user = userService.findByUserName(username);
        float balance = user.getBalance();
        if (totalprice>balance){
            return ResultFactory.buildSuccessResult(400);
        }else {
            float balance_after = balance-totalprice;
            userService.updateBalance(username,balance_after);
            cartService.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller);
            Integer id =  orderService.getNewOrderUnid();
            moneySevice.insertMoney(id,totalprice,username,seller);
            cartService.deleteCartByGoodsid(goodsid);
            return ResultFactory.buildSuccessResult(200);
        }
    }
}
