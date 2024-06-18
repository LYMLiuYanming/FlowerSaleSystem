package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart")
public class Cart {
    private String name;
    private String address;
    private String picture;
    private String username;
    private float price;
    private float totalprice;
    private Integer goodsid;
    private String raddress;
    private String seller;
    @TableId
    private Integer number;
    private Integer amount;

    public Cart(){}
}
