package com.lyn.dao;

import com.lyn.entity.message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface messageDao {
    @Select("select * from message")
    List<message> getAllMessage();
    @Select("select * from message where id= #{id}")
    message getMessage(@Param("id") Integer id);

    @Delete("delete from message where id= #{id}")
    int delMessage(@Param("id") Integer id);

    @Insert("insert into message (name,email,content,createTime)" +
         "values("+"#{name},#{email},#{content},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(message message);

   /*  @Update("update message SET id= #{id},name= #{name},email= #{email}, content= #{content}, createTime= #{createTime} " +
             "where id= #{id}")
    int update(message message);*/
}
