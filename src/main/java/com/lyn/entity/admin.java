package com.lyn.entity;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class admin {
   private Integer adminId;
   private String username;
   private String password;
   private String email;
   private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
   private Date lastLogin;
   private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
   private Date createTime;

    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
