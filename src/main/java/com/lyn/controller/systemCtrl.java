package com.lyn.controller;

import com.lyn.entity.Response;
import com.lyn.entity.admin;
import com.lyn.service.adminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class systemCtrl {
    @Resource
    private adminService adminService;

    @RequestMapping(value = "/back/system", method = {RequestMethod.GET, RequestMethod.POST})
    public String system(Model model) {
        List<admin> adminList = adminService.getAllAdmin();
        model.addAttribute("admin", adminList);
        return "back/system";
    }

    @RequestMapping("/delAdmin")
    public String delAdmin(@RequestParam("adminId") Integer adminId, Model model) {
        Response<admin> response = adminService.deleteAdmin(adminId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/system";
        } else {
            model.addAttribute("error", response.getMessage());
            return "back/system";
        }
    }

    @GetMapping("/back/toAddSys")
    public String toAddSystem(Model model) {
        List<admin> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList", adminList);
        return "back/systemAdd";
    }

    @PostMapping("/back/addSystem")
    public String addSystem(admin admin, Model model) {
        List<admin> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList", adminList);
        String msg = adminService.insert(admin).getMessage();
        model.addAttribute("msg", msg);
        return "back/systemAdd";
    }

    @GetMapping("/back/toEditSys")
    public String toEdit(Integer adminId, Model model) {
        admin admin1 = adminService.getAdmin(adminId);
        model.addAttribute("admin", admin1);
        return "back/systemEdit";
    }

    @PostMapping("/back/updateSystem")
    public String updateSystem(admin admin, Model model) {
        Response<admin> response = adminService.update(admin);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        } else {
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("admin", admin);
        return "back/systemEdit";
    }
}
