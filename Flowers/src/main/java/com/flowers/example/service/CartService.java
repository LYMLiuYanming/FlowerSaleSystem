package com.flowers.example.service;

import com.flowers.example.pojo.Cart;

import java.util.List;

public interface CartService {
    void insertCart(String name, float price, float totalprice, Integer amount, String picture, String address, Integer goodsid, String username, String raddress, String seller);
    Cart findCartByGoodsid(Integer goodsid);
    Cart findCartByNumber(Integer number);
    void insertCartOnlyAmount(Integer goodsid, float amount, float totalprice_after, String raddress, String seller);
    Cart findAmountByGoodsid(Integer goodsid);
    List<Cart> seatchAllCartByUsername(String username);
    void deleteCartByGoodsid(Integer goodsid);
    void changeCart(Integer amount, String raddress, float totalprice, Integer goodsid);
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice,String seller);
    void updateCart(String name, float price, float totalprice, String addrss, Integer goodsid);
}
