/*
package com.lyn.controller;

import com.lyn.entity.admin;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class adminCtrl {
    @Resource
    com.lyn.service.adminService adminService;
    */
/*@GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
*//*

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String doLogin(admin admin,Model model) {
    admin admin1 = adminService.get(admin);
        if (admin1 == null) {
        model.addAttribute("msg", "用户不存在");
        return "login";
    } else {
        model.addAttribute("admin", admin1);
        return "redirect:/backFirst/first";
    }
}
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String role,  // 前端传过来的角色：admin / user
                          HttpSession session,
                          Model model) {

        // 验证用户名密码（这里简化，实际要查数据库）
        if ("lecinna".equals(username) && "123456".equals(password) && "lecinna".equals(role)) {
            session.setAttribute("loginUser", username);
            session.setAttribute("userRole", "lecinna");  // 👈 保存角色
            return "redirect:/backFirst/first";
        } else if ("user".equals(username) && "123456".equals(password) && "user".equals(role)) {
            session.setAttribute("loginUser", username);
            session.setAttribute("userRole", "user");   // 👈 保存角色
            return "redirect:/admin/index";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 清除所有 Session
        return "redirect:/login";
    }
}
*/


/*
package com.lyn.controller;

import com.lyn.entity.admin;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class adminCtrl {

    @Resource
    com.lyn.service.adminService adminService;

    // ===== GET：跳转到登录页 =====
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    // ===== POST：处理登录 =====
        @PostMapping("/login")
        public String doLogin(
                @RequestParam String username,
                @RequestParam String password,
                @RequestParam(value = "role", defaultValue = "user") String role,
                HttpSession session,
                Model model) {

            // ===== 加这几行日志 =====
            System.out.println("====================");
            System.out.println("用户名: [" + username + "]");
            System.out.println("密码: [" + password + "]");
            System.out.println("角色: [" + role + "]");
            System.out.println("====================");

            // 管理员登录
            if ("lecinna".equals(username) && "123456".equals(password) && "admin".equals(role)) {
                session.setAttribute("loginUser", username);
                session.setAttribute("userRole", "admin");
                return "redirect:/backFirst/first";
            } else if ("user".equals(username) && "123456".equals(password) && "user".equals(role)) {
                session.setAttribute("loginUser", username);
                session.setAttribute("userRole", "user");
                return "redirect:/admin/index";
            } else {
                model.addAttribute("error", "用户名、密码或角色错误");
                return "login";
            }
        }
    // ===== 登出 =====
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}*/
package com.lyn.controller;

import com.lyn.entity.admin;
import com.lyn.service.adminService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/admin")
public class adminCtrl {

    @Resource
    private adminService adminService;

    // ===== 网站首页 =====
    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/backFirst/first";
        }
        return "login";
    }

    // ===== 显示登录页 =====
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    // ===== 处理登录 =====
    @PostMapping("/login")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(value = "role", defaultValue = "user") String role,
            HttpSession session,
            Model model) {

        System.out.println("====================");
        System.out.println("用户名: [" + username + "]");
        System.out.println("密码: [" + password + "]");
        System.out.println("角色: [" + role + "]");
        System.out.println("====================");

        // 从数据库查询用户（密码加密后匹配）
        admin user = adminService.findByUsernameAndPassword(username, password);

        if (user != null) {
            // status = 1 是管理员，status = 0 是普通用户
            String userRole = (user.getStatus() != null && user.getStatus() == 1) ? "admin" : "user";

            if (!userRole.equals(role)) {
                model.addAttribute("error", "角色选择错误，请选择正确的角色");
                return "login";
            }

            // 登录成功，更新最后登录时间
            user.setLastLogin(new Date());
            adminService.update(user);

            session.setAttribute("loginUser", username);
            session.setAttribute("userRole", userRole);

            if ("admin".equals(userRole)) {
                return "redirect:/backFirst/first";
            } else {
                return "redirect:/admin/index";
            }
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // ===== 显示注册页面 =====
    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    // ===== 处理注册 =====
    @PostMapping("/register")
    public String doRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(value = "role", defaultValue = "user") String role,
            Model model) {

        System.out.println("====================");
        System.out.println("注册 - 用户名: [" + username + "]");
        System.out.println("注册 - 密码: [" + password + "]");
        System.out.println("注册 - 角色: [" + role + "]");
        System.out.println("====================");

        // 1. 检查用户名是否为空
        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("error", "用户名不能为空");
            return "register";
        }

        // 2. 检查密码是否为空
        if (password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "密码不能为空");
            return "register";
        }

        // 3. 检查两次密码是否一致
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次输入的密码不一致");
            return "register";
        }

        // 4. 检查密码长度
        if (password.length() < 6) {
            model.addAttribute("error", "密码长度不能少于6位");
            return "register";
        }

        // 5. 检查用户名是否已存在
        if (adminService.existsByUsername(username)) {
            model.addAttribute("error", "用户名已存在，请换一个");
            return "register";
        }

        // 6. 保存用户到数据库（密码会在Service层自动加密）
        admin newUser = new admin();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setStatus("admin".equals(role) ? 1 : 0);
        newUser.setCreateTime(new Date());
        adminService.save(newUser);

        System.out.println("✅ 注册成功！");
        model.addAttribute("msg", "🎉 注册成功！请登录");
        return "redirect:/admin/login";
    }

    // ===== 登出 =====
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}