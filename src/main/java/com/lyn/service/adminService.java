/*
package com.lyn.service;

import com.lyn.entity.Response;
import com.lyn.entity.admin;

import java.util.List;

public interface adminService {
     // 获取所有管理员
     List<admin> getAllAdmin();
     // 获取某个管理员0
     admin get(admin admin);
     // 获取某个管理员
     admin getAdmin(Integer adminId);
     // 删除某个管理员
     Response<admin> deleteAdmin(Integer adminId);
     // 添加管理员
     Response<admin> insert(admin admin);
     // 修改管理员
     Response<admin> update(admin admin);


}
*/
package com.lyn.service;

import com.lyn.entity.Response;
import com.lyn.entity.admin;

import java.util.List;

public interface adminService {
     // 获取所有管理员
     List<admin> getAllAdmin();

     // 获取某个管理员
     admin get(admin admin);

     // 获取某个管理员
     admin getAdmin(Integer adminId);

     // 删除某个管理员
     Response<admin> deleteAdmin(Integer adminId);

     // 添加管理员
     Response<admin> insert(admin admin);

     // 修改管理员
     Response<admin> update(admin admin);

     // ===== 新增：根据用户名查询 =====
     admin findByUsername(String username);

     // ===== 新增：根据用户名和密码查询 =====
     admin findByUsernameAndPassword(String username, String password);

     // ===== 新增：检查用户名是否存在 =====
     boolean existsByUsername(String username);

     // ===== 新增：保存用户（注册用） =====
     int save(admin admin);
}