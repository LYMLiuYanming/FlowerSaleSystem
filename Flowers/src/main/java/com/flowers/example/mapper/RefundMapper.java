package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Refund;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RefundMapper extends BaseMapper<Refund> {
    @Insert("INSERT INTO `refund` (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,reason,orderid,seller) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},#{tag},#{price},#{totalprice},#{reason},#{orderid},#{seller})")
    void insertRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag);
    @Insert("INSERT INTO `adminrefund` (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,reason,orderid,seller) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},#{tag},#{price},#{totalprice},#{reason},#{orderid},#{seller})")
    void insertAdminRefund(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller, String tag);
    @Select("SELECT * from refund where username = #{username}")
    List<Refund> searchAllRefund(String username);
    @Select("SELECT * from refund where seller = #{seller}")
    List<Refund> searchAllRefundBySeller(String seller);
    @Delete("DELETE from adminrefund where orderid = #{orderid}")
    void deleteRefund(Integer orderid);
    @Select("SELECT * from adminrefund where seller = #{seller}")
    List<Refund> findAllAdminRefund(String seller);
    @Update("UPDATE adminrefund set refundstatus = #{refundstatus}, returnstatus = #{returnstatus} where orderid = #{orderid}")
    void changeAdminRefund(Integer orderid, String refundstatus, String returnstatus);
    @Update("UPDATE refund set refundstatus = #{refundstatus}, returnstatus = #{returnstatus} where orderid = #{orderid}")
    void changeRefund(Integer orderid, String refundstatus, String returnstatus);
    @Insert("INSERT INTO `refund` (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,reason,orderid,seller) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},'已收到货',#{price},#{totalprice},#{reason},#{orderid},#{seller})")
    void insertRefundHistory(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String reason, Integer orderid, String seller);
}
