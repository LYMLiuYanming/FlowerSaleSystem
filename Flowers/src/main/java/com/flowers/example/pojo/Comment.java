package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.annotation.Nullable;

@Data
@Nullable
public class Comment {
    private String content;
    private String username;
    @TableId
    private Integer orderid;
    private Integer goodsid;
    private String date;
    private String name;
    private String seller;

    public Comment(){}
}
