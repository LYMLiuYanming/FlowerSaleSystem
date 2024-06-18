package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("money")
public class Money {
    @TableId
    private Integer id;
    private float money;
    private String username;
    private String seller;

    public Money(){}
}
