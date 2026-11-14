package com.lyn.dao;

import com.lyn.entity.performance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface performanceDao {
   @Select("select * from performance")
    List<performance> getAllPerformance();
   @Select("select * from performance where perID= #{perId}")
    performance getPer(@Param("perId") Integer perId);

   @Delete("delete from performance where perID= #{perId}")
    int delPer(@Param("perId") Integer perId);
   @Insert("insert into performance (date,active,work)" +
           "values("+"#{date},#{active},#{work})")
    @Options(useGeneratedKeys = true, keyProperty = "perId")
    int insert(performance performance);
   @Update("update performance SET perId= #{perId},date= #{date}, active= #{active}, work= #{work} " +
            "where perId=#{perId}")
    int update(performance performance);
}



