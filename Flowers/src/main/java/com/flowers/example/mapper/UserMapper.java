package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * from user where username = #{username}")
    User findByUsername(String username);
    @Update("UPDATE user set password = #{newPass} where username=#{username} and password=#{password}}")
    Boolean changePassword(String newPass, String username, String password);
    @Update("UPDATE user set password = #{newPass} where username=#{username} and email=#{email}")
    void resetPassword(String username, String newPass, String email);
    @Insert("INSERT into user(username,password,create_time,update_time,email,role,balance,payWithPassWord) values (#{username},#{password},now(),now(),#{email},#{role},0,'是')")
    void add(String username, String password, String email, Integer role);
    @Select("SELECT raddress from user where username = #{username}")
    User findRAddressByUserName(String username);
    @Update("UPDATE user set username = #{username}, password = #{password}, email = #{email}, birthday = #{birthday}, raddress = #{raddress}, sex = #{sex}, update_time = now(), payWithPassWord = #{payWithPassWord} where id = #{id}")
    void updateUser(String username, String password, String email, String birthday, String raddress, String sex, Integer id, String payWithPassWord);
    @Update("UPDATE user set balance = #{balance} where username = #{username}")
    void updateBalance(String username, float balance);
    @Select("SELECT balance from user where username = #{username}")
    Float getBalance(String username);
    @Insert("INSERT INTO user(balance) VALUES (#{balance}) where username = #{username}")
    void insertBalance(float balance, String username);
}
