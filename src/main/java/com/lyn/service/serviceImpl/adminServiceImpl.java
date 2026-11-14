/*
package com.lyn.service.serviceImpl;

import com.lyn.dao.adminDao;
import com.lyn.entity.Response;
import com.lyn.entity.admin;
import com.lyn.service.adminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service("adminService")
public class adminServiceImpl implements adminService {
    @Resource
    private adminDao adminDao;

    @Override
    public List<admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }
    @Override
    public admin get(admin  admin) {
        return adminDao.get(admin);
    }

    @Override
    public admin getAdmin(Integer adminId) {
        return adminDao.getAdmin(adminId);
    }

    //删除
    @Override
    public Response<admin> deleteAdmin(Integer adminId) {
        Response<admin> res = null;
        int num = adminDao.deleteAdmin(adminId);
        if (num > 0) {
            res = Response.success("删除成功");
        } else {
            res = Response.fail("删除失败");
        }
        return res;
    }

    //添加
    @Override
    public Response<admin> insert(admin admin) {
        Response<admin> res = null;
        if (admin.getUsername() == null) {
            res = Response.fail("用户名不能为空");
            return res;
        }
        if (admin.getPassword() == null) {
            res = Response.fail("密码不能为空");
            return res;
        }
        String psw = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(psw);
        int num = adminDao.insert(admin);
        if (num > 0) {
            res = Response.success("添加成功");
        } else {
            res = Response.fail("添加失败");
        }
        return res;
    }

    //修改
    @Override
    public Response<admin> update(admin admin) {
        Response<admin> res = null ;
        if (admin.getUsername() == null) {
            res = Response.fail("用户名不能为空");
            return res;
        }
        if (admin.getPassword() == null) {
            res = Response.fail("密码不能为空");
            return res;
        }
        int num = adminDao.update(admin);
        if (num > 0) {
            res = Response.fail("修改成功");
        }else{
            res = Response.success("修改失败");
        }
        return res;
    }

}

*/
package com.lyn.service.serviceImpl;

import com.lyn.dao.adminDao;
import com.lyn.entity.Response;
import com.lyn.entity.admin;
import com.lyn.service.adminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service("adminService")
public class adminServiceImpl implements adminService {
    @Resource
    private adminDao adminDao;

    @Override
    public List<admin> getAllAdmin() {
        return adminDao.getAllAdmin();
    }

    @Override
    public admin get(admin admin) {
        return adminDao.get(admin);
    }

    @Override
    public admin getAdmin(Integer adminId) {
        return adminDao.getAdmin(adminId);
    }

    // 删除
    @Override
    public Response<admin> deleteAdmin(Integer adminId) {
        Response<admin> res = null;
        int num = adminDao.deleteAdmin(adminId);
        if (num > 0) {
            res = Response.success("删除成功");
        } else {
            res = Response.fail("删除失败");
        }
        return res;
    }

    // 添加
    @Override
    public Response<admin> insert(admin admin) {
        Response<admin> res = null;
        if (admin.getUsername() == null) {
            res = Response.fail("用户名不能为空");
            return res;
        }
        if (admin.getPassword() == null) {
            res = Response.fail("密码不能为空");
            return res;
        }
        String psw = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(psw);
        int num = adminDao.insert(admin);
        if (num > 0) {
            res = Response.success("添加成功");
        } else {
            res = Response.fail("添加失败");
        }
        return res;
    }

    // 修改
    @Override
    public Response<admin> update(admin admin) {
        Response<admin> res = null;
        if (admin.getUsername() == null) {
            res = Response.fail("用户名不能为空");
            return res;
        }
        if (admin.getPassword() == null) {
            res = Response.fail("密码不能为空");
            return res;
        }
        int num = adminDao.update(admin);
        if (num > 0) {
            res = Response.fail("修改成功");
        } else {
            res = Response.success("修改失败");
        }
        return res;
    }

    // ===== 新增：根据用户名查询 =====
    @Override
    public admin findByUsername(String username) {
        return adminDao.findByUsername(username);
    }

    // ===== 新增：根据用户名和密码查询 =====
    @Override
    public admin findByUsernameAndPassword(String username, String password) {
        // 把密码用MD5加密后再去数据库查询
        String encryptedPsw = DigestUtils.md5DigestAsHex(password.getBytes());
        return adminDao.findByUsernameAndPassword(username, encryptedPsw);
    }

    // ===== 新增：检查用户名是否存在 =====
    @Override
    public boolean existsByUsername(String username) {
        return adminDao.findByUsername(username) != null;
    }

    // ===== 新增：保存用户（注册用） =====
    @Override
    public int save(admin admin) {
        // 密码用MD5加密
        String encryptedPsw = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(encryptedPsw);
        return adminDao.insert(admin);
    }
}