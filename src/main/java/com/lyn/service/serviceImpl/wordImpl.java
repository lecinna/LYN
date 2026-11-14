package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.wordDao;
import com.lyn.entity.Response;
import com.lyn.entity.word;
import com.lyn.service.wordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wordService")
public class wordImpl implements wordService {
    @Resource
    private wordDao wordDao;
    @Override
    public List<word> getAllWord() {
        return wordDao.getAllWord();
    }
    @Override
    public PageInfo<word> getAllWord(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<word> word = wordDao.getAllWord();
        PageInfo<word> pageInfo = new PageInfo<>(word);
        return pageInfo;
    }

    @Override
    public word getWord(Integer wordId) {
        return wordDao.getWord(wordId);
    }
    @Override
    public Response<word> delWord(Integer wordId) {
        Response<word> response=null;
        int num = wordDao.delWord(wordId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }
    @Override
    public Response<word> insert(word word) {
        Response<word> response;
        int num = wordDao.insert(word);
        if (num > 0 ){
            response=Response.success("添加成功");
        }else{
            response=Response.fail("添加失败");
        }
        return response;
    }

    @Override
    public Response<word> update(word word) {
        Response<word> response;
        int num = wordDao.update(word);
        if (num > 0){
            response=Response.success("修改成功");
        }else{
            response=Response.fail("修改失败");
        }
        return response;
    }
}
