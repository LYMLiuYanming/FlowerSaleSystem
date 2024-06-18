package com.flowers.example.service;

import com.flowers.example.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> searchAllOrder(String username);

    List<Order> searchAllHistoryOrder(String username);

    Order searchRaddress(Integer number);

    void resetRaddress(String raddress, String tag,Integer number);
    void resetRaddressUn(String raddress, String tag,Integer number);
    void changeOrderReceived(String raddress, String tag, Integer number);

    void deleteOrder(Integer number);
    void deleteOrderUn(Integer number);
    void deleteHistoryOrder(Integer number);

    Order searchHistoryRaddress(Integer number);

    Object searchOrderBygoodsid(Integer goodsid);

    Order searchOrderByNumber(Integer number);
    Order searchOrderUnByNumber(Integer number);
    Order searchHistoryOrderByNumber(Integer orderid);
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller, Integer number);
    void submitOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller);
    void insertHistoryOrderReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String seller,Integer id);
    void insertHistoryOrderUnReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String reason, String seller,Integer id);
    List<Order> findAllAdminOrder(String seller);
    List<Order> findAllAdminOrderUnend(String seller);
    List<Order> findAllHistoryAdminOrder(String seller);
    List<Order> findAllAdminOrderUnendByUsername(String username);
    List<Map<String,Integer>> selectNumber();
    Integer getNewOrderid();
    Integer getNewOrderUnid();
    void updateOrderSubmit(String state, String reason, Integer number);
    void updateOrderPay(Integer number);
    List<Order> getOrderUnPaid(String username);
}
