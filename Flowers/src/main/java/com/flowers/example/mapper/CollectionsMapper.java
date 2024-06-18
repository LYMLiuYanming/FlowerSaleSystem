package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Collections;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionsMapper extends BaseMapper<Collections> {
    @Insert("INSERT INTO `collection` (name, address, price, goodsid, picture, username, raddress) VALUES (#{name},#{address},#{price},#{goodsid},#{picture},#{username},#{raddress})")
    void insertCollection(String name, String address, float price, Integer goodsid, String picture, String username, String raddress);
    @Select("SELECT * from collection where goodsid = #{goodsid}")
    Collections findCollectionByGoodsid(Integer goodsid);
    @Select("SELECT * from collection where username = #{username}")
    List<Collections> searchAllCollectionsByUsername(String username);
    @Insert("INSERT INTO `order_submit` (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,seller,pay,state) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},'未收到货',#{price},#{totalprice},#{seller},'未支付','未发货')")
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller);
    @Delete("DELETE from collection where goodsid = #{goodsid}")
    void cancelCollection(Integer goodsid);
    @Update("UPDATE collection set name = #{name}, price = #{price}, address = #{address} where goodsid = #{goodsid}")
    void updateCollections(String name, float price, String address, Integer goodsid);
}
