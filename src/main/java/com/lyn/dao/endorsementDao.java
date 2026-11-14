package com.lyn.dao;

import com.lyn.entity.endorsement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface endorsementDao {
    @Select("select * from endorsement")
    List<endorsement> getAllEndorsement();
    @Select("select * from endorsement where endorseId= #{endorseId}")
    endorsement getEndorsement(@Param("endorseId") Integer endorseId);

    @Delete("delete from endorsement where endorseId= #{endorseId}")
    int delEndorsement(@Param("endorseId") Integer endorseId);

    @Insert("insert into endorsement (name,time,image)" +
            "values("+"#{name},#{time},#{image})")
    @Options(useGeneratedKeys = true, keyProperty = "endorseId")
    int insert(endorsement endorsement);

    @Update("update endorsement SET endorseId= #{endorseId},name= #{name}, time= #{time}, image= #{image} " +
            "where endorseId= #{endorseId}")
    int update(endorsement endorsement);
}
