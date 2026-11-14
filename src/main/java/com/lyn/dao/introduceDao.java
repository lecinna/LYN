package com.lyn.dao;

import com.lyn.entity.introduce;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface introduceDao {
    @Select("select * from introduce")
    List<introduce> getAllIntroduce();
    //获取某个事件
    @Select("select * from introduce where introId= #{introId}")
    introduce get(@Param("introId") Integer introId);

    @Select("select * from introduce where incident=#{incident}")
    introduce getIntroduce( introduce introduce);
    //删除某个事件
    @Delete("delete from introduce where incident= #{incident}")
    int delIntro(@Param("incident") String incident);
    //添加事件
    @Insert("insert into introduce" +"(time,incident,image)"+
            "values("+
            "#{time},#{incident},#{image})")
    @Options(useGeneratedKeys = true, keyProperty = "incident")
    int insert(introduce introduce);
    //修改事件
    @Update("update introduce SET introId=#{introId},time=#{time}, incident=#{incident}, image=#{image} " +
            "where introId=#{introId}")
    int update(introduce introduce);
}
