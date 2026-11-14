package com.lyn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.introduce;
import com.lyn.service.introduceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class introduceCtrl {
    @Resource
    private introduceService introduceService;

    @RequestMapping(value = "/back/introduce", method = {RequestMethod.GET, RequestMethod.POST})
    public String getIntroduce(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  Model model) {
        PageInfo<introduce> introducePage = introduceService.getAllIntroduce(pageNum, pageSize);
        model.addAttribute("introducePage", introducePage);
        return "back/introduce";
    }
    @RequestMapping("/delIntro")
    public String delIntroduce(@RequestParam("incident") String incident, Model model) {
        Response<introduce> response = introduceService.delIntro(incident);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/introduce";
        } else {
            model.addAttribute("error", response.getMessage());
            return "back/introduce";
        }
    }
    @GetMapping("/back/toAddIntro")
    public String toAddIntro(Model model) {
        return "back/introduceAdd";
    }
    @PostMapping("/back/addIntro")
    public String addIntro(introduce introduce, Model model) {
        String msg = introduceService.insert(introduce).getMessage();
        model.addAttribute("msg", msg);
        return "back/introduceAdd";
    }

    @GetMapping("/back/toEditIntro")
    public String toEdit(Integer introId, Model model) {
        introduce introduce = introduceService.get(introId);
        model.addAttribute("introduce", introduce);
        return "back/introduceEdit";
    }
    @PostMapping("/back/updateIntro")
    public String updateIntro(introduce introduce, Model model) {
        Response<introduce> response = introduceService.update(introduce);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("introduce", introduce);
        return "back/introduceEdit";
    }
}
