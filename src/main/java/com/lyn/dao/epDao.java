package com.lyn.dao;

import com.lyn.entity.ep;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface epDao {
    @Select("select * from ep")
    List<ep> getAllEp();
    @Select("select * from ep where eId=#{eId}")
    ep getEp(@Param("eId") Integer eId);

    @Delete("delete from ep where eId= #{eId}")
    int delEp(@Param("eId") Integer eId);
    @Insert("insert into ep (eId,name,sname,description,time,image)" +
            "values("+"#{eId},#{name},#{sname},#{description},#{time},#{image})")
    @Options(useGeneratedKeys = true, keyProperty = "eId")
    int insert(ep ep);
    @Update("update ep SET eId= #{eId},name= #{name}, sname= #{sname}, description= #{description}, time= #{time}, image= #{image} " +
            "where eId= #{eId}")
    int update(ep ep);
}
