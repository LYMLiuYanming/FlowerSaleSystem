package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class Refund {
    @TableId
    private Integer number;
    private String date;
    private String username;
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
    @Nullable
    private String reason;
    @Nullable
    private String refundstatus;
    @Nullable
    private String returnstatus;

    public Refund(){}
}
