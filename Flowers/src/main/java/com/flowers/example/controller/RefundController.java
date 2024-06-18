package com.flowers.example.controller;

import com.flowers.example.pojo.Order;
import com.flowers.example.pojo.Refund;
import com.flowers.example.pojo.User;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.service.MoneySevice;
import com.flowers.example.service.OrderService;
import com.flowers.example.service.RefundService;
import com.flowers.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RefundController {
    @Autowired
    private RefundService refundService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MoneySevice moneySevice;
    @Autowired
    private UserService userService;
    @PostMapping("/getAllRefund")
    public Result getAllRefund(@RequestBody Refund refund){
        String username = refund.getUsername();
        Object refundList = refundService.searchAllRefund(username);
        return ResultFactory.buildSuccessResult(refundList);
    }
    @PostMapping("/getAllAdminRefund")
    public Result getAllAdminRefund(@RequestBody Refund refund){
        String seller = refund.getUsername();
        Object adminRefundList = refundService.searchAllRefundBySeller(seller);
        return ResultFactory.buildSuccessResult(adminRefundList);
    }
    @PostMapping("/deleteRefund")
    public Result deleteRefund(@RequestBody Refund refund){
        Integer orderid = refund.getOrderid();
        refundService.deleteRefund(orderid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/returnFlower")
    public Result returnFlower(@RequestBody Refund refund){
        Integer orderid = refund.getOrderid();
        String reason = refund.getReason();
        String tag = refund.getTag();
        Order o = orderService.searchHistoryOrderByNumber(orderid);
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
        refundService.insertRefund(name,address,goodsid,amount,username,raddress,price,totalprice,reason,orderid,seller,tag);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/admin/changeRefund")
    public Result changeAdminRefund(@RequestBody Refund refund){
        Integer orderid = refund.getOrderid();
        String refundstatus = refund.getRefundstatus();
        String returnstatus = refund.getReturnstatus();
        String seller = refund.getSeller();
        String tag = refund.getTag();
        refundService.changeRefund(orderid,refundstatus,returnstatus);
        if (Objects.equals(refundstatus,"已退款")&&Objects.equals(tag,"已收到货")){
            Order order = orderService.searchHistoryOrderByNumber(orderid);
            Integer id = order.getId();
            String username = order.getUsername();
            float balance = moneySevice.getBalance(id);
            User user = userService.findByUserName(seller);
            float balance_before = user.getBalance();
            // float balance_before = userService.getBalance(seller);
            User user1 = userService.findByUserName(username);
            float balance_user_before = user1.getBalance();
            // float balance_user_before = userService.getBalance(username);
            float balance_user_after = balance_user_before+balance;
            balance = balance_before - balance;
            userService.updateBalance(seller,balance);
            userService.updateBalance(username,balance_user_after);
        }
        return ResultFactory.buildSuccessResult(200);
    }
}
