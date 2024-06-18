package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT * from `order` where username = #{username}")
    List<Order> searchAllOrder(String username);
    @Select("SELECT * from `historyorder` where username = #{username}")
    List<Order> searchAllHistoryOrder(String username);
    @Select("SELECT raddress from `order` where number = #{number}")
    Order searchRaddress(Integer number);
    @Update("UPDATE `order` set raddress = #{raddress}, tag = #{tag} where number = #{number}")
    void resetRaddress(String raddress, String tag, Integer number);
    @Update("UPDATE `order_submit` set raddress = #{raddress}, tag = #{tag} where number = #{number}")
    void resetRaddressUn(String raddress, String tag, Integer number);
    @Update("UPDATE `order` set raddress = #{raddress}, tag = #{tag}, rdate = now() where number = #{number}")
    void changeOrderReceived(String raddress, String tag, Integer number);
    @Delete("DELETE FROM `order` where number = #{number}")
    void deleteOrder(Integer number);
    @Delete("DELETE FROM `order_submit` where number = #{number}")
    void deleteOrderUn(Integer number);
    @Delete("DELETE FROM `historyorder` where number = #{number}")
    void deleteHistoryOrder(Integer number);
    @Select("SELECT raddress from `historyorder` where number = #{number}")
    Order searchHistoryRaddress(Integer number);
    @Select("SELECT * from `order` where goodsid = #{goodsid}")
    List<Order> searchOrderBygoodsid(Integer goodsid);
    @Select("SELECT * from `order` where number = #{number}")
    Order searchOrderByNumber(Integer number);
    @Select("SELECT * from `order_submit` where number = #{number}")
    Order searchOrderUnByNumber(Integer number);
    @Select("SELECT * from historyorder where orderid = #{orderid}")
    Order searchHistoryOrderByNumber(Integer orderid);
    @Insert("INSERT INTO `order` (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,seller,id) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},'未收到货',#{price},#{totalprice},#{seller},#{number})")
    void insertOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller, Integer number);
    @Insert("INSERT INTO `historyorder` (name,address,goodsid,amount,rdate,username,raddress,tag,price,totalprice,date,orderid,seller,id) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},'已收到货',#{price},#{totalprice},#{date},#{orderid},#{seller},#{id})")
    void insertHistoryOrderReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String seller,Integer id);
    @Insert("INSERT INTO `historyorder` (name,address,goodsid,amount,username,raddress,tag,price,totalprice,date,orderid,reason,seller,id) VALUES (#{name},#{address},#{goodsid},#{amount},#{username},#{raddress},'未收到货',#{price},#{totalprice},#{date},#{orderid},#{reason},#{seller},#{id})")
    void insertHistoryOrderUnReceived(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String date, Integer orderid, String reason, String seller, Integer id);
    @Select("SELECT * from `order` where seller = #{seller}")
    List<Order> findAllAdminOrder(String seller);
    @Select("SELECT * from historyorder where seller = #{seller}")
    List<Order> findAllHistoryAdminOrder(String seller);
    @Select("SELECT name,SUM(number)  from historyorder group by name")
    List<Map<String,Integer>> selectNumber();
    @Select("SELECT * from `order_submit` where seller = #{seller}")
    List<Order> findAllAdminOrderUnend(String seller);
    @Select("SELECT * from `order_submit` where username = #{username}")
    List<Order> findAllAdminOrderUnendByUsername(String username);
    @Insert("Insert into order_submit (name,address,goodsid,amount,date,username,raddress,tag,price,totalprice,seller,pay,state) VALUES (#{name},#{address},#{goodsid},#{amount},now(),#{username},#{raddress},'未收到货',#{price},#{totalprice},#{seller},'未支付','未发货')")
    void submitOrder(String name, String address, Integer goodsid, Integer amount, String username, String raddress, float price, float totalprice, String seller);
    @Select("SELECT MAX(number) from `order`")
    Integer getNewOrderid();
    @Select("SELECT MAX(number) from `order_submit`")
    Integer getNewOrderUnid();
    @Update("UPDATE order_submit set state = #{state}, reason = #{reason} where number = #{number}")
    void updateOrderSubmit(String state, String reason, Integer number);
    @Update("UPDATE order_submit set pay = '已支付' where number = #{number}")
    void updateOrderPay(Integer number);
    @Select("SELECT * from order_submit where username = #{username} and pay = '未支付'")
    List<Order> getOrderUnPaid(String username);
}
