package com.flowers.example.service.impl;

import com.flowers.example.mapper.OrderMapper;
import com.flowers.example.pojo.Order;
import com.flowers.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> searchAllOrder(String username){
        return orderMapper.searchAllOrder(username);
    }
    @Override
    public List<Order> searchOrderBygoodsid(Integer goodsid){
        return orderMapper.searchOrderBygoodsid(goodsid);
    }
    @Override
    public Order searchOrderByNumber(Integer number){
        return orderMapper.searchOrderByNumber(number);
    }
    @Override
    public Order searchOrderUnByNumber(Integer number){
        return orderMapper.searchOrderUnByNumber(number);
    }
    @Override
    public Order searchHistoryOrderByNumber(Integer orderid){
        return orderMapper.searchHistoryOrderByNumber(orderid);
    }
    @Override
    public List<Order> searchAllHistoryOrder(String username){
        return orderMapper.searchAllHistoryOrder(username);
    }
    @Override
    public Order searchRaddress(Integer number){
        return orderMapper.searchRaddress(number);
    }
    @Override
    public void resetRaddress(String raddress, String tag, Integer number){
        orderMapper.resetRaddress(raddress,tag,number);
    }
    @Override
    public void resetRaddressUn(String raddress, String tag, Integer number){
        orderMapper.resetRaddressUn(raddress,tag,number);
    }
    @Override
    public void changeOrderReceived(String raddress, String tag, Integer number){
        orderMapper.changeOrderReceived(raddress,tag,number);
    }
    @Override
    public void deleteOrder(Integer number){
        orderMapper.deleteOrder(number);
    }
    @Override
    public void deleteOrderUn(Integer number){
        orderMapper.deleteOrderUn(number);
    }
    @Override
    public void deleteHistoryOrder(Integer number){
        orderMapper.deleteHistoryOrder(number);
    }
    @Override
    public Order searchHistoryRaddress(Integer number){
        return orderMapper.searchHistoryRaddress(number);
    }
    @Override
    public void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller, Integer number){
        orderMapper.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller, number);
    }
    @Override
    public void submitOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller){
        orderMapper.submitOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller);
    }
    @Override
    public void insertHistoryOrderReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String seller,Integer id){
        orderMapper.insertHistoryOrderReceived(name,address,goodsid,amount,username,raddress,price,totalprice,date,orderid,seller,id);
    }
    @Override
    public void insertHistoryOrderUnReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String reason, String seller,Integer id){
        orderMapper.insertHistoryOrderUnReceived(name,address,goodsid,amount,username,raddress,price,totalprice,date,orderid,reason,seller,id);
    }
    @Override
    public List<Order> findAllAdminOrder(String seller){
        return orderMapper.findAllAdminOrder(seller);
    }
    @Override
    public List<Order> findAllAdminOrderUnend(String seller){
        return orderMapper.findAllAdminOrderUnend(seller);
    }
    @Override
    public List<Order> findAllHistoryAdminOrder(String seller){
        return orderMapper.findAllHistoryAdminOrder(seller);
    }
    @Override
    public List<Order> findAllAdminOrderUnendByUsername(String username){
        return orderMapper.findAllAdminOrderUnendByUsername(username);
    }
    @Override
    public List<Map<String,Integer>> selectNumber(){
        return orderMapper.selectNumber();
    }
    @Override
    public Integer getNewOrderid(){
        return orderMapper.getNewOrderid();
    }
    @Override
    public Integer getNewOrderUnid(){
        return orderMapper.getNewOrderUnid();
    }
    @Override
    public void updateOrderSubmit(String state, String reason, Integer number){
        orderMapper.updateOrderSubmit(state,reason,number);
    }
    @Override
    public void updateOrderPay(Integer number){
        orderMapper.updateOrderPay(number);
    }
    @Override
    public List<Order> getOrderUnPaid(String username){
        return orderMapper.getOrderUnPaid(username);
    }
}
