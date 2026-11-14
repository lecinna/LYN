package com.lyn.frontPlatform;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeCtrl {

    // ===== 网站首页 =====
    @GetMapping("/")
    public String home(HttpSession session) {
        // 如果已登录，跳转到后台
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/backFirst/first";
        }
        // 未登录显示登录页
        return "login";
    }
}

