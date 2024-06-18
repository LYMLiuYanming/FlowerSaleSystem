package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.annotation.Nullable;

@Data
@TableName("order")
public class Order {
    @TableId
    private Integer number;
    private String date;
    private String username;
    private String password;
    private String name;
    private String address;
    private String raddress;
    private String tag;
    private Integer goodsid;
    private Integer amount;
    private float price;
    private float totalprice;
    private Integer orderid;
    private String seller;
    private Integer id;
    private String state;
    private String pay;
    @Nullable
    private String reason;
    @Nullable
    private String rdate;

    public Order(){}
}
