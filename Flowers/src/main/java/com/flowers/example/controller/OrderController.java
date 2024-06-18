package com.flowers.example.controller;

import cn.hutool.core.codec.Base64;
import com.flowers.example.pojo.Flower;
import com.flowers.example.pojo.Order;
import com.flowers.example.pojo.User;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultJson;
import com.flowers.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {
    String E = "error";
    @Autowired
    private OrderService orderService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private UserService userService;
    @Autowired
    private MoneySevice moneySevice;
    @Autowired
    private FlowerService flowerService;
    @PostMapping("/getAllOrder")
    public Result getAllOrder(@RequestBody User user){
        String username = user.getUsername();
        Object orderList = orderService.searchAllOrder(username);
        return ResultFactory.buildSuccessResult(orderList);
    }
    @PostMapping("/getOrderBygoodsid")
    public Result getOrderBygoodsid(@RequestBody Order order){
        Integer goodsid = order.getGoodsid();
        Object orderList = orderService.searchOrderBygoodsid(goodsid);
        return ResultFactory.buildSuccessResult(orderList);
    }
    @PostMapping("/getAllHistoryOrder")
    public Result getAllHistoryOrder(@RequestBody User user){
        String username = user.getUsername();
        Object historyorderList = orderService.searchAllHistoryOrder(username);
        return ResultFactory.buildSuccessResult(historyorderList);
    }
    @PostMapping("/resetRaddress")
    public Result resetRaddress(@RequestBody Order order){
        String raddress = order.getRaddress();
        Integer number = order.getNumber();
        String tag = order.getTag();
        Order order1 = orderService.searchOrderByNumber(number);
        String username = order1.getUsername();
        String date = order1.getDate();
        String name = order1.getName();
        String address = order1.getAddress();
        Integer goodsid = order1.getGoodsid();
        Integer amount = order1.getAmount();
        String seller = order1.getSeller();
        Integer id = order1.getId();
        float price = order1.getPrice();
        float totalprice = order1.getTotalprice();
        if (Objects.equals(tag, "已收到货")){
            orderService.insertHistoryOrderReceived(name,address,goodsid,amount,username,raddress,price,totalprice,date,number,seller,id);
            float balance = moneySevice.getBalance(id);
            float balance_before = userService.getBalance(seller);
            balance = balance_before+balance;
            userService.updateBalance(seller,balance);
            orderService.deleteOrder(number);
        } else {
            orderService.resetRaddress(raddress,tag,number);
        }
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/deleteOrder")
    public Result deleteOrder(@RequestBody Order order){
        String reason = order.getReason();
        Integer number = order.getNumber();
        Order o = orderService.searchOrderByNumber(number);
        String name = o.getName();
        String address = o.getAddress();
        Integer goodsid = o.getGoodsid();
        Integer amount = o.getAmount();
        String username = o.getUsername();
        String raddress = o.getRaddress();
        String date = o.getDate();
        float price = o.getPrice();
        float totalprice = o.getTotalprice();
        String seller = o.getSeller();
        Integer id = o.getId();
        String tag = "未收到货";
        float balance = moneySevice.getBalance(id);
        float balance_before = userService.getBalance(username);
        balance = balance_before + balance;
        userService.updateBalance(username,balance);
        refundService.insertRefund(name,address,goodsid,amount,username,raddress,price,totalprice,reason,number,seller,tag);
        orderService.insertHistoryOrderUnReceived(name,address,goodsid,amount,username,raddress,price,totalprice,date,number,reason,seller,id);
        orderService.deleteOrder(number);
        Order order1 = orderService.searchRaddress(number);
        if (order1 == null){
            return ResultFactory.buildSuccessResult(200);
        } else {
            return ResultFactory.buildFailResult(E);
        }
    }
    @PostMapping("/deleteHistoryOrder")
    public Result deleteHistoryOrder(@RequestBody Order order){
        Integer number = order.getNumber();
        orderService.deleteHistoryOrder(number);
        Order order1 = orderService.searchHistoryRaddress(number);
        if (order1 == null){
            return ResultFactory.buildSuccessResult(200);
        } else {
            return ResultFactory.buildFailResult(E);
        }
    }
    @PostMapping("getAllAdminOrder")
    public Result getAllAdminOrder(@RequestBody User user){
        String seller = user.getUsername();
        Object AdminOrderList = orderService.findAllAdminOrder(seller);
        return ResultFactory.buildSuccessResult(AdminOrderList);
    }
    @PostMapping("getAllAdminUnsendOrder")
    public Result getAllAdminUnsendOrder(@RequestBody User user){
        String seller = user.getUsername();
        Object AdminOrderList = orderService.findAllAdminOrderUnend(seller);
        return ResultFactory.buildSuccessResult(AdminOrderList);
    }
    @PostMapping("/getAllHistoryAdminOrder")
    public Result findAllHistoryAdminOrder(@RequestBody User user){
        String seller = user.getUsername();
        Object AdminHistoryOrderList = orderService.findAllHistoryAdminOrder(seller);
        return ResultFactory.buildSuccessResult(AdminHistoryOrderList);
    }
    @PostMapping("showEcharts1")
    public ResultJson<?> pie(){
        List<Map<String, Integer>> maps = orderService.selectNumber();
        return ResultJson.success(maps);
    }
    @PostMapping("sendOrder")
    public Result sendOrder(@RequestBody Order order){
        Integer number = order.getNumber();
        Order o = orderService.searchOrderUnByNumber(number);
        String name = o.getName();
        String address = o.getAddress();
        Integer goodsid = o.getGoodsid();
        Integer amount = o.getAmount();
        String username = o.getUsername();
        String raddress = o.getRaddress();
        float price = o.getPrice();
        float totalprice = o.getTotalprice();
        String seller = o.getSeller();
        orderService.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller,number);
        orderService.deleteOrderUn(number);
        Flower flower = flowerService.searchFlowerBuy(goodsid);
        Integer sales = flower.getSales();
        sales=sales+amount;
        flowerService.updateSales(sales,goodsid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("deleteOrderUn")
    public Result deleteOrderUn(@RequestBody Order order){
        Integer number = order.getNumber();
        String reason = order.getReason();
        Order order1 = orderService.searchOrderUnByNumber(number);
        String username = order1.getUsername();
        float balance = userService.getBalance(username);
        float balance_this = order1.getTotalprice();
        balance = balance+balance_this;
        userService.updateBalance(username,balance);
        // orderService.deleteOrderUn(number);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("getOrderUnsByUsername")
    public Result getOrderUnsByUsername(@RequestBody Order order){
        String username = order.getUsername();
        Object orderList = orderService.findAllAdminOrderUnendByUsername(username);
        return ResultFactory.buildSuccessResult(orderList);
    }
    @PostMapping("resetRaddressUn")
    public Result resetRaddressUn(@RequestBody Order order){
        String tag = "未收到货";
        String raddress = order.getRaddress();
        Integer number = order.getNumber();
        orderService.resetRaddressUn(raddress,tag,number);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("deleteOrderUnByReason")
    public Result deleteOrderUnByReason(@RequestBody Order order){
        Integer number = order.getNumber();
        String reason = order.getReason();
        String state = "未发货";
        Order order1 = orderService.searchOrderUnByNumber(number);
        String username = order1.getUsername();
        float balance = userService.getBalance(username);
        float balance_this = order1.getTotalprice();
        balance = balance+balance_this;
        userService.updateBalance(username,balance);
//        orderService.deleteOrderUn(number);
        orderService.updateOrderSubmit(state,reason,number);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/conFirmPay")
    public Result conFirmPay(@RequestBody Order order){
        Integer number = order.getNumber();
        String password = order.getPassword();
        String username = order.getUsername();
        String Base64Password = Base64.encode(password);
        User user = userService.findByUserName(username);
        String password1 = user.getPassword();
        if (Base64Password.equals(password1)){
            orderService.updateOrderPay(number);
            return ResultFactory.buildSuccessResult(200);
        }
        return ResultFactory.buildSuccessResult(400);
    }
    @PostMapping("/getOrderUnPaid")
    public Result getOrderUnPaid(@RequestBody Order order){
        String username = order.getUsername();
        Object orderList = orderService.getOrderUnPaid(username);
        return ResultFactory.buildSuccessResult(orderList);
    }
}
