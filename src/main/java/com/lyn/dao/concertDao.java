package com.lyn.dao;


import com.lyn.entity.concert;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface concertDao {

        // 获取所有演唱会
        @Select("SELECT * FROM concert")
        List<concert> getAllConcert();

       // 获取即将开始的演唱会（status=1）
         /*@Select("SELECT * FROM concert WHERE id = #{id} ")
        List<concert> getUpcoming();*/

        // 根据ID查询
        @Select("SELECT * FROM concert WHERE id = #{id}")
        concert getById(@Param("id") Integer id);

        // 插入演唱会
        @Insert("INSERT INTO concert (title, city, venue, date, description, image, status) " +
                "VALUES (#{title}, #{city}, #{venue}, #{date},#{description}, #{image}, #{status})")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        int insert(concert concert);

        // 更新演唱会
        @Update("UPDATE concert SET title = #{title},city = #{city}, venue = #{venue}, " +
                "date = #{date}, description = #{description}, image = #{image}, " +
                "status = #{status} WHERE id = #{id}")
        int update(concert concert);

        // 删除演唱会
        @Delete("DELETE FROM concert WHERE id = #{id}")
        int delete(@Param("title") String title);

        // 更新状态（即将开演 → 已结束）
        @Update("UPDATE concert SET status = 2 WHERE date < NOW() AND status = 1")
        int autoUpdateStatus();
    }
