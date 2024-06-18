package com.flowers.example.controller;

import com.flowers.example.pojo.Comment;
import com.flowers.example.response.Result;
import com.flowers.example.response.ResultFactory;
import com.flowers.example.service.CommentService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static cn.hutool.core.date.DateUtil.now;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/insertComment")
    public Result insertComment(@RequestBody Comment comment){
        Integer orderid = comment.getOrderid();
        Integer goodsid = comment.getGoodsid();
        String content = comment.getContent();
        String username = comment.getUsername();
        String name = comment.getName();
        String date = now();
        commentService.insertComment(content,username,orderid,date,goodsid,name);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/getAllComment")
    public Result getAllComment(@RequestBody Comment comment){
        String username = comment.getUsername();
        Object commentList = commentService.findCommentByUsername(username);
        return ResultFactory.buildSuccessResult(commentList);
    }
    @PostMapping("changeComment")
    public Result changeComment(@RequestBody Comment comment){
        Integer orderid = comment.getOrderid();
        String content = comment.getContent();
        commentService.changeComment(orderid,content);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("deleteComment")
    public Result deleteComment(@RequestBody Comment comment){
        Integer orderid = comment.getOrderid();
        commentService.deleteComment(orderid);
        return ResultFactory.buildSuccessResult(200);
    }
    @PostMapping("/findCommentAdmin")
    public Result findComment(@RequestBody Comment comment){
        String seller = comment.getSeller();
        Object commentList = commentService.findCommentBySeller(seller);
        return ResultFactory.buildSuccessResult(commentList);
    }
    @PostMapping("/findCommentByGoodsid")
    public Result find(@RequestBody Comment comment){
        Integer goodsid = comment.getGoodsid();
        Object commentList = commentService.searchContent(goodsid);
        return ResultFactory.buildSuccessResult(commentList);
    }
}
