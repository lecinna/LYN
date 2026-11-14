package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.word;

import java.util.List;

public interface wordService {
    List<word> getAllWord();
    PageInfo<word> getAllWord(Integer pageNum, Integer pageSize);
    word getWord(Integer wordId);

    Response<word> delWord(Integer wordId);
    Response<word> insert(word word);
    Response<word> update(word word);
}
