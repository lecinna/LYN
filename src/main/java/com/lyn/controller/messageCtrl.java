package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.message;
import com.lyn.service.messageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class messageCtrl {
    @Resource
    private messageService messageService;

    @RequestMapping(value = "/back/message",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllMessage(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                Model model){
        PageInfo<message> messagePage = messageService.getAllMessage(pageNum, pageSize);
        model.addAttribute("messagePage", messagePage);
        return "back/message";
    }
    @RequestMapping("/delMessage")
    public String delMessage(@RequestParam("id") Integer id, Model model) {
        Response<message> response = messageService.delMessage(id);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/message";
        } else {
            model.addAttribute("message", response.getMessage());
        }
        return "back/message";
    }

    @GetMapping("/back/toAddMessage")
    public String toAddMessage(Model model) {
        List<message> messageList = messageService.getAllMessage();
        model.addAttribute("messageList", messageList);
        return "back/messageAdd";
    }
    @PostMapping("/back/addMessage")
    public String addMessage(message message, Model model) {
        List<message> messageList = messageService.getAllMessage();
        model.addAttribute("messageList", messageList);
        String msg = messageService.insert(message).getMessage();
        model.addAttribute("msg", msg);
        return "back/messageAdd";
    }
    /*@GetMapping("/back/toEditMessage")
    public String toEdit(Integer id, Model model) {
        message message = messageService.getMessage(id);
        model.addAttribute("message", message);
        return "back/messageEdit";
    }
    @PostMapping("/back/updateMessage")
    public String updateMessage(message message, Model model) {
        Response<message> response = messageService.update(message);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("message", message);
        return "back/messageEdit";
    }
*/
}
