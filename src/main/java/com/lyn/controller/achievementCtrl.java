package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.achievement;
import com.lyn.service.achievementService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class achievementCtrl {
    @Resource
    private achievementService achievementService;
    @RequestMapping(value = "/back/achievement", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllAchievement(@RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    Model model) {
        PageInfo<achievement> achievement = achievementService.getAllAchievement(pageNum,pageSize);
        model.addAttribute("achievementPage", achievement);
        return "back/achievement";
    }

    @RequestMapping("/delAchievement")
    public String delAchievement(@RequestParam("achieveId") Integer achieveId, Model model) {
        Response<achievement> response = achievementService.delAchievement(achieveId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/achievement";
        } else {
            model.addAttribute("error", response.getMessage());
        }
        return "back/achievement";
    }

    @GetMapping("/back/toAddAchievement")
    public String toAddAchievement(Model model) {
        return "back/achievementAdd";
    }
    @PostMapping("/back/addAchievement")
    public String addAchievement(achievement achievement, Model model) {
        String msg = achievementService.insert(achievement).getMessage();
        model.addAttribute("msg", msg);
        return "back/achievementAdd";
    }

    @GetMapping("/back/toEditAchievement")
    public String toEdit(Integer achievementId, Model model) {
        achievement achievement = achievementService.getAchievement(achievementId);
        model.addAttribute("achievement", achievement);
        return "back/achievementEdit";
    }
    @PostMapping("/back/updateAchievement")
    public String updateAchievement(achievement achievement, Model model) {
        Response<achievement> response = achievementService.update(achievement);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("achievement", achievement);
        return "back/achievementEdit";
    }
}
