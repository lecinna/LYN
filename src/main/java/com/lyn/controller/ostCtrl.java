package com.lyn.controller;


import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.ost;
import org.springframework.ui.Model;
import com.lyn.service.ostService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class ostCtrl {
    @Resource
    private ostService ostService;

    @RequestMapping(value = "/back/ost",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAll(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         Model model){
        PageInfo<ost> ostPage = ostService.getAllOst(pageNum, pageSize);
        model.addAttribute("ostPage", ostPage);
        return "back/ost";
    }
    @RequestMapping("/delOst")
    public String delOst(@RequestParam("oId") Integer oID, Model model) {
        Response<ost> response = ostService.delOst(oID);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/ost";
        }else{
            model.addAttribute("error", response.getMessage());
            return "back/ost";
        }
    }
    @GetMapping("/back/toAddOst")
    public String toAddOst(Model model) {
        return "back/ostAdd";
    }
    @PostMapping("/back/addOst")
    public String addOst(ost ost, Model model) {
        String msg = ostService.insert(ost).getMessage();
        model.addAttribute("msg", msg);
        return "back/ostAdd";
    }

    @GetMapping("/back/toEditOst")
    public String toEdit(Integer oId, Model model) {
        ost ost = ostService.getOst(oId);
        model.addAttribute("ost", ost);
        return "back/ostEdit";
    }
    @PostMapping("/back/updateOst")
    public String updateOst(ost ost, Model model) {
        Response<ost> response = ostService.update(ost);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("ost", ost);
        return "back/ostEdit";
    }
}
