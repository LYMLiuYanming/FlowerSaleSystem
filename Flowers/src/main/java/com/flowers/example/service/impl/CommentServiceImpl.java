package com.flowers.example.service.impl;

import com.flowers.example.mapper.CommentMapper;
import com.flowers.example.pojo.Comment;
import com.flowers.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public void insertComment(String content, String username, Integer orderid, String date, Integer goodsid, String name){
        commentMapper.insertComment(content,username,orderid,date,goodsid,name);
    }
    @Override
    public List<Comment> findCommentByUsername(String username){
        return commentMapper.findCommentByUsername(username);
    }
    @Override
    public void changeComment(Integer orderid, String content){
        commentMapper.changeComment(orderid,content);
    }
    @Override
    public void deleteComment(Integer orderid){
        commentMapper.deleteComment(orderid);
    }
    @Override
    public List<Comment> findCommentBySeller(String seller){
        return commentMapper.findCommentBySeller(seller);
    }
    @Override
    public List<Comment> searchContent(Integer goodsid){
        return commentMapper.searchContent(goodsid);
    }
}
