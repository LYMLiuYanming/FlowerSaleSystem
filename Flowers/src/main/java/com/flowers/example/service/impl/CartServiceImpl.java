package com.flowers.example.service.impl;

import com.flowers.example.mapper.CartMapper;
import com.flowers.example.pojo.Cart;
import com.flowers.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Override
    public void insertCart(String name, float price, float totalprice, Integer amount, String picture, String address, Integer goodsid, String username, String raddress, String seller){
        cartMapper.insertCart(name, price, totalprice, amount, picture, address, goodsid, username, raddress, seller);
    };
    @Override
    public Cart findCartByGoodsid(Integer goodsid){
        return cartMapper.findCartByGoodsid(goodsid);
    }
    @Override
    public Cart findCartByNumber(Integer number){
        return cartMapper.findCartByNumber(number);
    }
    @Override
    public void insertCartOnlyAmount(Integer goodsid, float amount, float totalprice_after, String raddress, String seller){
        cartMapper.insertCartOnlyAmount(goodsid,amount, totalprice_after,raddress,seller);
    }
    @Override
    public Cart findAmountByGoodsid(Integer goodsid){
        return cartMapper.findAmountByGoodsid(goodsid);
    }
    @Override
    public List<Cart> seatchAllCartByUsername(String username){
        return cartMapper.seatchAllCartByUsername(username);
    }
    @Override
    public void deleteCartByGoodsid(Integer goodsid){
        cartMapper.deleteCartByGoodsid(goodsid);
    }
    @Override
    public void changeCart(Integer amount, String raddress, float totalprice, Integer goodsid){
        cartMapper.changeCart(amount, raddress, totalprice, goodsid);
    }
    @Override
    public void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice,String seller){
        cartMapper.insertOrder(name,address,goodsid,amount,username,raddress,price,totalprice,seller);
    }
    @Override
    public void updateCart(String name, float price, float totalprice,String addrss, Integer goodsid){
        cartMapper.updateCart(name,price,totalprice,addrss,goodsid);
    }
}
