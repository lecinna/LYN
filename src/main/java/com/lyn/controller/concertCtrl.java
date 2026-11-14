package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.concert;
import com.lyn.entity.Response;
import com.lyn.service.concertService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loginBack")
public class concertCtrl {

    @Resource
    private concertService concertService;

    // ===== 演唱会列表页 =====
    @RequestMapping(value = "/back/concert", method = {RequestMethod.GET, RequestMethod.POST})
    public String getConcert(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             Model model) {
        PageInfo<concert> concertPage = concertService.getAllConcert(pageNum, pageSize);
        model.addAttribute("concertPage", concertPage);
        return "back/concert";
    }

    // ===== 添加演唱会页面 =====
    @GetMapping("/back/toAdd")
    public String toAdd() {
        return "back/concertAdd";
    }

    // ===== 添加演唱会提交 =====
    @PostMapping("/back/addConcert")
    public String addConcert(concert concert, Model model) {
        Response<concert> res = concertService.insert(concert);
        if (res.isSuccess()) {
            model.addAttribute("msg", "✅ " + res.getMessage());
        } else {
            model.addAttribute("msg", "❌ " + res.getMessage());
        }
        return "back/concertAdd";
    }

    // ===== 修改演唱会页面 =====
    @GetMapping("/back/toEdit")
    public String toEdit(@RequestParam Integer id, Model model) {
        concert concert = concertService.getById(id);
        model.addAttribute("concert", concert);
        return "back/concertEdit";
    }

    // ===== 修改演唱会提交 =====
    @PostMapping("/back/editConcert")
    public String edit(concert concert, Model model) {
        Response<concert> res = concertService.update(concert);
        if (res.isSuccess()) {
            model.addAttribute("msg", "✅ " + res.getMessage());
        } else {
            model.addAttribute("msg", "❌ " + res.getMessage());
        }
        model.addAttribute("concert", concert);
        return "back/concertEdit";
    }

    // ===== 删除演唱会 =====
    @PostMapping("/back/delete")
    public String delete(String title, Model model) {
        Response<concert> res = concertService.delConcert(title);
        if (res.isSuccess()) {
            model.addAttribute("msg", "✅ " + res.getMessage());
        } else {
            model.addAttribute("msg", "❌ " + res.getMessage());
        }
        return "redirect:/loginBack/back/concert";
    }

    // ===== 获取演唱会详情（API） =====
    @GetMapping("/back/detail/{id}")
    @ResponseBody
    public concert detail(@PathVariable Integer id) {
        return concertService.getById(id);
    }

}