package com.flowers.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowers.example.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Insert("INSERT INTO comment (content, username, orderid, date, goodsid, name) VALUES (#{content},#{username},#{orderid},#{date},#{goodsid},#{name})")
    void insertComment(String content, String username, Integer orderid, String date, Integer goodsid, String name);
    @Select("SELECT * from comment where username = #{username}")
    List<Comment> findCommentByUsername(String username);
    @Update("UPDATE comment set content = #{content} where orderid = #{orderid}")
    void changeComment(Integer orderid, String content);
    @Delete("DELETE from comment where orderid = #{orderid}")
    void deleteComment(Integer orderid);
    @Select("SELECT * from comment where seller = #{seller}")
    List<Comment> findCommentBySeller(String seller);
    @Select("SELECT * from comment where goodsid = #{goodsid}")
    List<Comment> searchContent(Integer goodsid);
}
