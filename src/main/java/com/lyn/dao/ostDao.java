package com.lyn.dao;

import com.lyn.entity.ost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ostDao {
    @Select("select * from ost")
    List<ost> getAllOst();
    @Select("select * from ost where oId= #{oId}")
    ost getOst(@Param("oId") Integer oId);

    @Delete("delete from ost where oId= #{oId}")
    int delOst(@Param("oId") Integer oId);

    @Insert("insert into ost (time,opera,song,musicPath)" +
            "values("+"#{time},#{opera},#{song},#{musicPath})")
    @Options(useGeneratedKeys = true, keyProperty = "oId")
    int insert(ost ost);

    @Update("update ost SET oId= #{oId},time= #{time}, opera= #{opera}, song= #{song}, musicPath= #{musicPath} " +
            "where oId= #{oId}")
    int update(ost ost);
}
