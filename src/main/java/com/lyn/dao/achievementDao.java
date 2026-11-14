package com.lyn.dao;

import com.lyn.entity.achievement;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface achievementDao {
    @Select("select * from achievement")
    List<achievement> getAllAchievement();
    @Select("select * from achievement where achieveId= #{achieveId}")
    achievement getAchievement(@Param("achieveId") Integer achieveId);

    @Delete("delete from achievement where achieveId= #{achieveId}")
    int delAchievement(@Param("achieveId") Integer achieveId);

    @Insert("insert into achievement (incident,endorse,image)" +
            "values("+"#{incident},#{endorse},#{image})")
    @Options(useGeneratedKeys = true, keyProperty = "achieveId")
    int insert(achievement achievement);

    @Update("update achievement SET achieveId= #{achieveId},incident= #{incident}, image= #{image}, endorse= #{endorse} " +
            "where achieveId= #{achieveId}")
    int update(achievement achievement);




}
