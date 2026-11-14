/*
package com.lyn.dao;

import com.lyn.entity.admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface adminDao {
    @Select("select * from admin")
    List<admin> getAllAdmin();

    @Select("select * from admin where username= #{username}")
    admin get(admin admin);

    @Select("select * from admin where adminId=#{adminId}")
    admin getAdmin(@Param("adminId") Integer adminId);

    @Delete("delete from admin where adminId=#{adminId}")
    int deleteAdmin(@Param("adminId") Integer adminId);

    @Insert("insert into admin" +"(adminId,username,password,email,phone,last_login,status,create_time)"+
            "values("+
            "#{adminId},#{username}"+",#{password}"+",#{email}"+",#{phone}"+
            ",#{lastLogin}"+",#{status}"+",#{createTime}"+")")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    int insert(admin admin);

    @Update("update admin SET adminId=#{adminId},username=#{username},password=#{password},email=#{email},phone=#{phone},last_login=#{lastLogin},"+
    "status=#{status},create_time=#{createTime} where adminId=#{adminId}")
    int update(admin admin);
}*/
package com.lyn.dao;

import com.lyn.entity.admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface adminDao {

    @Select("select * from admin")
    List<admin> getAllAdmin();

    @Select("select * from admin where username = #{username}")
    admin get(admin admin);

    @Select("select * from admin where adminId = #{adminId}")
    admin getAdmin(@Param("adminId") Integer adminId);

    // ===== 新增：根据用户名查询 =====
    @Select("select * from admin where username = #{username}")
    admin findByUsername(@Param("username") String username);

    // ===== 新增：根据用户名和密码查询 =====
    @Select("select * from admin where username = #{username} and password = #{password}")
    admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Delete("delete from admin where adminId = #{adminId}")
    int deleteAdmin(@Param("adminId") Integer adminId);

    @Insert("insert into admin (username, password, email, phone, last_login, status, create_time) " +
            "values (#{username}, #{password}, #{email}, #{phone}, #{lastLogin}, #{status}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    int insert(admin admin);

    @Update("update admin set username = #{username}, password = #{password}, email = #{email}, " +
            "phone = #{phone}, last_login = #{lastLogin}, status = #{status}, create_time = #{createTime} " +
            "where adminId = #{adminId}")
    int update(admin admin);
}