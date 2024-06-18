package com.flowers.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId
    private Integer id;
    private Integer role;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String birthday;
    @Nullable
    private String userPic;
    @Nullable
    private float balance;
    @Nullable
    private String raddress;
    private String payWithPassWord;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

    public User(){}
}


