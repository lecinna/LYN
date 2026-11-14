package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.single;
import com.lyn.service.singleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class singleCtrl {
    @Resource
    private singleService singleService;

    @RequestMapping(value = "/back/single", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllSingle(@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               Model model) {
        PageInfo<single> singlePage = singleService.getAllSingle(pageNum, pageSize);
        model.addAttribute("singlePage", singlePage);
        return "back/single";
    }
    @RequestMapping("/delSingle")
    public String delSingle(@RequestParam("singleId") Integer singleId, Model model) {
        Response<single> response = singleService.delSingle(singleId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/single";
        } else {
            model.addAttribute("error", response.getMessage());
            return "back/single";
        }
    }

    @GetMapping("/back/toAddSingle")
    public String toAddSingle(Model model) {
        return "back/singleAdd";
    }
    @PostMapping("/back/addSingle")
    public String addSingle(single single, Model model) {
        String msg = singleService.insert(single).getMessage();
        model.addAttribute("msg", msg);
        return "back/singleAdd";
    }

    @GetMapping("/back/toEditSingle")
    public String toEdit(Integer singleId, Model model) {
        single single = singleService.getSingle(singleId);
        model.addAttribute("single", single);
        return "back/singleEdit";
    }
    @PostMapping("/back/updateSingle")
    public String updateSingle(single single, Model model) {
        Response<single> response = singleService.update(single);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("single", single);
        return "back/singleEdit";
    }
}
