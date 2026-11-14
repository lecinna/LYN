package com.lyn.controller;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.action;
import com.lyn.entity.word;
import com.lyn.service.wordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/loginBack")
public class wordCtrl {
    @Resource
    private wordService wordService;

    @RequestMapping(value = "/back/word",method = {RequestMethod.GET, RequestMethod.POST})
    public String getAllWord(@RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             Model model) {
        PageInfo<word> wordPage = wordService.getAllWord(pageNum, pageSize);
        model.addAttribute("wordPage", wordPage);
        return "back/word";
    }
    @RequestMapping("/delWord")
    public String delWord(@RequestParam("wordId") Integer wordId, Model model) {
        Response<word> response = wordService.delWord(wordId);
        if (response.isSuccess()) {
            model.addAttribute("message", response.getMessage());
            return "redirect:/loginBack/back/word";
        } else {
            model.addAttribute("error", response.getMessage());
            return "back/word";
        }
    }
    @GetMapping("/back/toAddWord")
    public String toAddWord(Model model) {
        return "back/wordAdd";
    }
    @PostMapping("/back/addWord")
    public String addWord(word word, Model model) {
        String msg = wordService.insert(word).getMessage();
        model.addAttribute("msg", msg);
        return "back/wordAdd";
    }
    @GetMapping("/back/toEditWord")
    public String toEdit(Integer wordId, Model model) {
        word word = wordService.getWord(wordId);
        model.addAttribute("word", word);
        return "back/wordEdit";
    }
    @PostMapping("/back/updateWord")
    public String updateWord(word word, Model model) {
        Response<word> response = wordService.update(word);
        if (response==null) {
            model.addAttribute("msg", "修改失败");
        }else{
            model.addAttribute("msg", "修改成功");
        }
        model.addAttribute("word", word);
        return "back/wordEdit";
    }
}
