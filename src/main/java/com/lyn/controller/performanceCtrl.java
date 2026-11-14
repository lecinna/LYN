package com.lyn.controller;


import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.performance;
import com.lyn.service.performanceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class performanceCtrl {
    @Resource
    private performanceService performanceService;

    @RequestMapping(value = "/back/perform", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPer(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         Model model) {
        PageInfo<performance> performancePage = performanceService.getAllPerformance(pageNum, pageSize);
        model.addAttribute("performancePage", performancePage);
        return "back/perform";
    }
    @RequestMapping("/delPer")
    public String delPer(@RequestParam("perId") Integer perID, Model model) {
        Response<performance> response = performanceService.delPer(perID);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/perform";
        } else {
            model.addAttribute("error", response.getMessage());
        }
        return "back/perform";
    }
    @GetMapping("/back/toAddPer")
    public String toAddPer(Model model) {
        return "back/performAdd";
    }
    @PostMapping("/back/addPer")
    public String addPer(performance performance, Model model) {
        String msg = performanceService.insert(performance).getMessage();
        model.addAttribute("msg", msg);
        return "back/performAdd";
    }

    @GetMapping("/back/toEditPer")
    public String toEdit(Integer perId, Model model) {
        performance performance = performanceService.getPer(perId);
        model.addAttribute("performance", performance);
        return "back/performEdit";
    }
    @PostMapping("/back/updatePer")
    public String updatePer(performance performance, Model model) {
        Response<performance> response = performanceService.update(performance);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("performance", performance);
        return "back/performEdit";
    }
}
