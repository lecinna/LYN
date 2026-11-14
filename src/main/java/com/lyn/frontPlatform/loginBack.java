package com.lyn.frontPlatform;



import com.github.pagehelper.PageInfo;
import com.lyn.entity.*;
import com.lyn.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class loginBack {
    @GetMapping("/doLogin")
    public String loginBack() {
        return "login";
    }

    @Resource
    private introduceService introduceService;

    @RequestMapping(value = "/admin/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllIntroduce(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  Model model) {
        PageInfo<introduce> introducePage = introduceService.getAllIntroduce(pageNum, pageSize);
        model.addAttribute("introducePage", introducePage);
        return "index";
    }
    @Resource
    private performanceService performanceService;
    @RequestMapping(value = "/admin/performance", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPer(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                         Model model) {
        PageInfo<performance> performancePage = performanceService.getAllPerformance(pageNum, pageSize);
        model.addAttribute("performancePage", performancePage);
        return "performance";
    }

    @Resource
    private ostService ostService;
    @RequestMapping(value = "/admin/music/ost", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllOst(@RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            Model model) {
        PageInfo<ost> ostPage = ostService.getAllOst(pageNum, pageSize);
        model.addAttribute("ostPage", ostPage);
        return "ost";
    }

    @Resource
    private epService epService;
    @RequestMapping(value = "/admin/music/ep", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllEp(Model model){
        List <ep> ep = epService.getAllEp();
        model.addAttribute("ep", ep);
        return "ep";
    }
    @Resource
    private singleService singleService;
    @RequestMapping(value = "/admin/music/single", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllSingle(@RequestParam(value = "pageSize", required = false, defaultValue = "15") Integer pageSize,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               Model model) {
        PageInfo<single> singlePage = singleService.getAllSingle(pageNum, pageSize);
        model.addAttribute("singlePage", singlePage);
        return "single";
    }

    @Resource
    private actionService actionService;
    @RequestMapping(value = "/admin/action", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllAction(@RequestParam(value = "pageSize", required = false, defaultValue = "16") Integer pageSize,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               Model model) {
        PageInfo<action> actionPage = actionService.getAllAction(pageNum, pageSize);
        model.addAttribute("actionPage", actionPage);
        return "action";
    }

    @Resource
    private wordService wordService;
    @RequestMapping(value = "/admin/word",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllWord(Model model) {
        List<word> word = wordService.getAllWord();
        model.addAttribute("word", word);
        return "word";
    }

    @Resource
    private endorsementService endorsementService;
    @RequestMapping(value = "/admin/prize/endorsement", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllEndorsement(@RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    Model model) {
        PageInfo<endorsement> endorsementPage = endorsementService.getAllEndorsement(pageNum, pageSize);
        model.addAttribute("endorsementPage", endorsementPage);
        return "endorsement";
    }

    @Resource
    private achievementService achievementService;
    @RequestMapping(value = "/admin/prize/achievement", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllAchievement(@RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize,
                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    Model model) {
        PageInfo<achievement> achievement = achievementService.getAllAchievement(pageNum,pageSize);
        model.addAttribute("achievementPage", achievement);
        return "achievement";
    }

    @Resource
    private messageService messageService;
    @RequestMapping(value = "/admin/message", method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllMessage(Model model) {
        List<message> messageList = messageService.getAllMessage();
        model.addAttribute("messageList", messageList);
        return "message";
    }

    @PostMapping("/admin/message/add")
    public String addMessage(message message, Model model) {
        Response<message> response = messageService.insert(message);
        if (response.isSuccess()) {
            model.addAttribute("successMsg", "留言成功！感谢您的留言！");
        } else {
            model.addAttribute("errorMsg", "留言失败，请稍后再试！");
        }
        List<message> messageList = messageService.getAllMessage();
        model.addAttribute("messageList", messageList);
        return "message";
    }

    @Resource
    private concertService concertService;

    // ===== 前台：演唱会展示页面 =====
    @RequestMapping(value = "/admin/concert", method = {RequestMethod.GET, RequestMethod.POST})
    public String concert(@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          Model model) {
        PageInfo<concert> concertPage = concertService.getAllConcert(pageNum, pageSize);

        // ===== 打印日志看看数据 =====
        System.out.println("===== 演唱会数据 =====");
        for (concert item : concertPage.getList()) {
            System.out.println("标题: " + item.getTitle());
            System.out.println("图片路径: " + item.getImage());
            System.out.println("---");
        }

        model.addAttribute("concertPage", concertPage);
        return "concert";
    }

    @GetMapping("/backFirst/first")
    public String backFirst() {
        return "backFirst";
    }
}
