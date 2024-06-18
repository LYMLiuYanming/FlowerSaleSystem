package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@TableName("goods")
public class Flower {
    private String name;
    private String address;
    private float price;
    @TableId
    private Integer goodsid;
    private String picture;
    private Integer number;
    private String seller;
    private Integer sales;
    private Integer value;

    private String username;

    public Flower(){}
}
