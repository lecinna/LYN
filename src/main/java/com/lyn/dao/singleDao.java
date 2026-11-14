package com.lyn.dao;

import com.lyn.entity.single;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface singleDao {
    @Select("select * from single")
    List<single> getAllSingle();
    @Select("select * from single where singleId= #{singleId}")
    single getSingle(@Param("singleId") Integer singleId);

    @Delete("delete from single where singleId= #{singleId}")
    int delSingle(@Param("singleId") Integer singleId);

    @Insert("insert into single(name,time,music) values(#{name},#{time},#{music})")
    int insert(single single);

    @Update("update single set name=#{name},time=#{time},music=#{music} where singleId=#{singleId}")
    int update(single single);
}
