package com.flowers.example.service;

import com.flowers.example.pojo.Comment;

import java.util.List;

public interface CommentService {
    void insertComment(String content, String username, Integer orderid, String date, Integer goodsid, String name);
    List<Comment> findCommentByUsername(String username);
    void changeComment(Integer orderid, String content);
    void deleteComment(Integer orderid);
    List<Comment> findCommentBySeller(String seller);
    List<Comment> searchContent(Integer goodsid);
}
