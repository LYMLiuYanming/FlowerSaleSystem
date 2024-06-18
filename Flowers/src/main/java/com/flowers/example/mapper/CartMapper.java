package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Insert("INSERT INTO `cart` (name, price, totalprice, amount, picture, address, goodsid, username, raddress, seller) values (#{name},#{price},#{totalprice},#{amount},#{picture},#{address},#{goodsid},#{username},#{raddress},#{seller})")
    void insertCart(String name, float price, float totalprice, Integer amount, String picture, String address, Integer goodsid, String username, String raddress, String seller);
    @Select("SELECT * from cart where goodsid = #{goodsid}")
    Cart findCartByGoodsid(Integer goodsid);
    @Select("SELECT * from cart where number = #{number}")
    Cart findCartByNumber(Integer number);
    @Update("UPDATE cart set amount = #{amount}, totalprice = #{totalprice_after}, raddress = #{raddress}, seller = #{seller} where goodsid = #{goodsid}")
    void insertCartOnlyAmount(Integer goodsid, float amount, float totalprice_after, String raddress, String seller);
    @Select("SELECT amount from cart where goodsid = #{goodsid}")
    Cart findAmountByGoodsid(Integer goodsid);
    @Select("SELECT * from cart where username = #{username}")
    List<Cart> seatchAllCartByUsername(String username);
    @Delete("DELETE from cart where goodsid = #{goodsid}")
    void deleteCartByGoodsid(Integer goodsid);
    @Update("UPDATE cart set amount = #{amount}, totalprice = #{totalprice}, raddress = #{raddress} where goodsid = #{goodsid}")
    void changeCart(Integer amount, String raddress, float totalprice, Integer goodsid);
    @Insert("INSERT INTO `order_submit` (name, price, totalprice, amount, tag, address, goodsid, username, date, raddress, seller, pay, state) values (#{name},#{price},#{totalprice},#{amount},'未收到货',#{address},#{goodsid},#{username},now(),#{raddress},#{seller},'未支付','未发货')")
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller);
    @Update("UPDATE cart set name = #{name}, price = #{price}, totalprice=#{totalprice}, address = #{address} where goodsid = #{goodsid}")
    void updateCart(String name, float price, float totalprice,String addrss, Integer goodsid);
}
