package com.lyn.dao;

import com.lyn.entity.action;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface actionDao {
    @Select("select * from action")
    List<action> getAllAction();
    @Select("select * from action where actId= #{actId}")
    action getAction(@Param("actId") Integer actId);

    @Delete("delete from action where actId= #{actId}")
    int delAction(@Param("actId") Integer actId);

    @Insert("insert into action (name,image,videoPath)" +
    "values("+"#{name},#{image},#{videoPath})")
    @Options(useGeneratedKeys = true, keyProperty = "actId")
    int insert(action action);

    @Update("update action SET actId= #{actId},name= #{name}, image= #{image}, videoPath= #{videoPath} " +
    "where actId=#{actId}")
    int update(action action);
}
