package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.endorsement;
import com.lyn.service.endorsementService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class endorsementCtrl {
    @Resource
    private endorsementService endorsementService;

   @RequestMapping(value = "/back/endorsement",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllEndorsement(@RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    Model model){
        PageInfo<endorsement> endorsementPage = endorsementService.getAllEndorsement(pageNum, pageSize);
        model.addAttribute("endorsementPage", endorsementPage);
        return "back/endorsement";
    }
    @RequestMapping("/delEndorsement")
    public String delEndorsement(@RequestParam("endorseId") Integer endorseId, Model model) {
        Response<endorsement> response = endorsementService.delEndorsement(endorseId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/endorsement";
        } else {
            model.addAttribute("message", response.getMessage());
        }
        return "back/endorsement";
    }

    @GetMapping("/back/toAddEndorsement")
    public String toAddEndorsement(Model model) {
        return "back/endorsementAdd";
    }
    @PostMapping("/back/addEndorsement")
    public String addEndorsement(endorsement endorsement, Model model) {
        String msg = endorsementService.insert(endorsement).getMessage();
        model.addAttribute("msg", msg);
        return "back/endorsementAdd";
    }
    @GetMapping("/back/toEditEndorsement")
    public String toEdit(Integer endorseId, Model model) {
        endorsement endorsement = endorsementService.getEndorsement(endorseId);
        model.addAttribute("endorsement", endorsement);
        return "back/endorsementEdit";
    }
    @PostMapping("/back/updateEndorsement")
    public String updateEndorsement(endorsement endorsement, Model model) {
        Response<endorsement> response = endorsementService.update(endorsement);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("endorsement", endorsement);
        return "back/endorsementEdit";
    }
}
