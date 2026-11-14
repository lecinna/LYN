package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.action;
import com.lyn.service.actionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class actionCtrl {
    @Resource
    private actionService actionService;

    @RequestMapping(value = "/back/action", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllAction(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               Model model) {
        PageInfo<action> actionPage = actionService.getAllAction(pageNum, pageSize);
        model.addAttribute("actionPage", actionPage);
        return "back/action";
    }

    @RequestMapping("/delAction")
    public String delAction(@RequestParam("actId") Integer actionId, Model model) {
        Response<action> response = actionService.delAction(actionId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/action";
        } else {
            model.addAttribute("error", response.getMessage());
            return "back/action";
        }
    }

    @GetMapping("/back/toAddAction")
    public String toAddAction(Model model) {
        return "back/actionAdd";
    }
    @PostMapping("/back/addAction")
    public String addAction(action action, Model model) {
        String msg = actionService.insert(action).getMessage();
        model.addAttribute("msg", msg);
        return "back/actionAdd";
    }
    @GetMapping("/back/toEditAction")
    public String toEdit(Integer actionId, Model model) {
        action action = actionService.getAction(actionId);
        model.addAttribute("action", action);
        return "back/actionEdit";
    }
    @PostMapping("/back/updateAction")
    public String updateAction(action action, Model model) {
        Response<action> response = actionService.update(action);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("action", action);
        return "back/actionEdit";
    }
}
