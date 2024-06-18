package com.flowers.example.service.impl;

import com.flowers.example.mapper.RefundMapper;
import com.flowers.example.pojo.Refund;
import com.flowers.example.service.RefundService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;
    @Override
    public void insertRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag){
        refundMapper.insertRefund(name,address,goodsid,amount,username,raddress,price,totalprice,reason,orderid,seller,tag);
    }
    @Override
    public void insertAdminRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag){
        refundMapper.insertAdminRefund(name,address,goodsid,amount,username,raddress,price,totalprice,reason,orderid,seller,tag);
    }
    @Override
    public List<Refund> searchAllRefund(String username){
        return refundMapper.searchAllRefund(username);
    }
    @Override
    public List<Refund> searchAllRefundBySeller(String seller){
        return refundMapper.searchAllRefundBySeller(seller);
    }
    @Override
    public void deleteRefund(Integer orderid){
        refundMapper.deleteRefund(orderid);
    }
    @Override
    public List<Refund> findAllAdminRefund(String seller){
        return refundMapper.findAllAdminRefund(seller);
    }
    @Override
    public void changeAdminRefund(Integer orderid, String refundstatus, String returnstatus){
        refundMapper.changeAdminRefund(orderid,refundstatus,returnstatus);
    }
    @Override
    public void changeRefund(Integer orderid, String refundstatus, String returnstatus){
        refundMapper.changeRefund(orderid,refundstatus,returnstatus);
    }
    @Override
    public void insertRefundHistory(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller){
        refundMapper.insertRefundHistory(name,address,goodsid,amount,username,raddress,price,totalprice,reason,orderid,seller);
    }
}
