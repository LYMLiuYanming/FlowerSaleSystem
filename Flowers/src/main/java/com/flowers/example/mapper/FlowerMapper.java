package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Flower;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowerMapper extends BaseMapper<Flower> {
    @Select("SELECT * FROM `goods`")
    List<Flower> searchAllFlower();
    @Select("SELECT * from goods where goodsid = #{goodsid}")
    List<Flower> searchFlower(Integer goodsid);
    @Select("SELECT * from goods where goodsid = #{goodsid}")
    Flower searchFlowerBuy(Integer goodsid);
    @Select("SELECT * from goods where name like #{name}")
    List<Flower> searchFlowerByName(String name);
    @Select("SELECT * from goods where seller = #{seller}")
    List<Flower> searchFlowerBySeller(String seller);
    @Insert("INSERT INTO goods (name, address, price, picture, seller) VALUES (#{name},#{address},#{price},#{picture},#{seller})")
    void insertFlower(String name, String address, float price, String picture, String seller);
    @Insert("INSERT INTO goods (name, address, price, seller) VALUES (#{name},#{address},#{price},#{seller});")
    void insertFlower2(String name, String address, float price, String seller);
    @Delete(" DELETE from goods where goodsid = #{goodsid}")
    void deleteFlower(Integer goodsid);
    @Update("UPDATE goods set name = #{name}, address = #{address}, price = #{price} where goodsid = #{goodsid} and seller = #{seller}")
    void changeFlower(String name, String address, float price, String seller, Integer goodsid);
    @Update("UPDATE goods set picture = #{picture} where goodsid = #{goodsid}")
    void changeFlowerPicture(String picture, Integer goodsid);
    @Select("SELECT max(goodsid) from goods")
    Integer getNewGoodsid();
    @Update("UPDATE goods set value = #{goodsid} where goodsid = #{goodsid}")
    void updateValue(Integer goodsid);
    @Update("UPDATE goods set sales = #{sales}, value = #{sales} where goodsid = #{goodsid}")
    void updateSales(Integer sales, Integer goodsid);
}
