package com.flowers.example.service;

import com.flowers.example.pojo.Refund;

import java.util.List;

public interface RefundService {
    void insertRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag);
    void insertAdminRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag);
    List<Refund> searchAllRefund(String username);
    List<Refund> searchAllRefundBySeller(String seller);
    void deleteRefund(Integer orderid);
    List<Refund> findAllAdminRefund(String seller);
    void changeAdminRefund(Integer orderid, String refundstatus, String returnstatus);
    void changeRefund(Integer orderid, String refundstatus, String returnstatus);
    void insertRefundHistory(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller);
}
