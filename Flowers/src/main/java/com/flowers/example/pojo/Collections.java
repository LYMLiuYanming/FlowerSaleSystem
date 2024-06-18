package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collection")
public class Collections {
    private String name;
    private String address;
    private String username;
    private float price;
    private Integer goodsid;
    private String picture;
    private String raddress;

    public Collections(){}
}
