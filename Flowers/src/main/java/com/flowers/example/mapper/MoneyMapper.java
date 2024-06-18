package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Money;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MoneyMapper extends BaseMapper<Money> {
    @Insert("INSERT INTO `money` (id, money, username, seller) VALUES (#{id},#{money},#{username},#{seller})")
    void insertMoney(Integer id, float money, String username, String seller);
    @Select("SELECT money from money where id = #{id}")
    Money searchMoney(Integer id);
    @Select("SELECT money from money where id = #{id}")
    float getBalance(Integer id);
}
