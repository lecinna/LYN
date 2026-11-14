package com.lyn.controller;


import com.lyn.entity.Response;
import com.lyn.entity.ep;
import com.lyn.service.epService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class epCtrl {
    @Resource
    private epService epService;

    @RequestMapping(value = "/back/ep",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAll(Model model){
        List ep = epService.getAllEp();
        model.addAttribute("ep", ep);
        return "back/ep";
    }
    @RequestMapping("/delEp")
    public String delEp(@RequestParam("eId") Integer eId, Model model) {
        Response<ep> response = epService.delEp(eId);
        if (response.isSuccess()){
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/ep";
        }else{
            model.addAttribute("error", response.getMessage());
            return "back/ep";
        }
    }

    @GetMapping("/back/toAddEp")
    public String toAddEp(Model model) {
        return "back/epAdd";
    }
    @PostMapping("/back/addEp")
    public String addEp(ep ep, Model model) {
        String msg = epService.insert(ep).getMessage();
        model.addAttribute("msg", msg);
        return "back/epAdd";
    }
    @GetMapping("/back/toEditEp")
    public String toEdit(Integer eId, Model model) {
        ep ep = epService.getEp(eId);
        model.addAttribute("ep", ep);
        return "back/epEdit";
    }
    @PostMapping("/back/updateEp")
    public String updateEp(ep ep, Model model) {
        Response<ep> response = epService.update(ep);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("ep", ep);
        return "back/epEdit";
    }
}
