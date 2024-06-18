package com.flowers.example.controller;

import cn.hutool.core.codec.Base64;
import com.flowers.example.pojo.User;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.result.Results;
import com.flowers.example.service.UserService;
import com.flowers.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Results register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        Integer role = user.getRole();
        String Base64Password = Base64.encode(password);
        User u = userService.findByUserName(username);
        if (u == null){
            userService.register(username,Base64Password,email,role);
            return new Results(200);
        }else {
            return new Results(400);
        }
    }

    @PostMapping("/login")
    public Results login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String Base64Password = Base64.encode(password);
        User loginUser = userService.findByUserName(username);
        if (loginUser == null){
            return new Results(400);
        }
        if (Base64Password.equals(loginUser.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return new Results(200);
        }
        return new Results(400);
    }

    @PostMapping("/resetPassword")
    public Results resetPassword(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String Base64Password = Base64.encode(password);
        String email = user.getEmail();
        User loginUser = userService.findByUserName(username);
        if (loginUser == null){
            return new Results(300);
        }
        if (email.equals(loginUser.getEmail())){
            userService.resetPassword(username,Base64Password,email);
            return new Results(200);
        }
        return new Results(400);
    }
    @PostMapping("/getUserRole")
    public Result getUserRole(@RequestBody User user){
        String username = user.getUsername();
        User u = userService.findByUserName(username);
        Integer role = u.getRole();
        return ResultFactory.buildSuccessResult(role);
    }
    @PostMapping("/getUser")
    public Result getUser(@RequestBody User user){
        String username = user.getUsername();
        User u = userService.findByUserName(username);
        return ResultFactory.buildSuccessResult(u);
    }
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        String Base64Password = Base64.encode(password);
        String email = user.getEmail();
        String birthday = user.getBirthday();
        String raddress = user.getRaddress();
        String sex = user.getSex();
        Integer id = user.getId();
        String payWithPassWord = user.getPayWithPassWord();
        userService.updateUser(username,Base64Password,email,birthday,raddress,sex,id,payWithPassWord);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/updateBalance")
    public Result updateBalance(@RequestBody User user){
        String username = user.getUsername();
        float balance = user.getBalance();
        userService.updateBalance(username,balance);
        return ResultFactory.buildSuccessResult(200);
    }
}
