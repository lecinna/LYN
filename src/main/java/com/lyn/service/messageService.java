package com.lyn.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.message;

import java.util.List;

public interface messageService {
    List<message> getAllMessage();

    PageInfo<message> getAllMessage(Integer pageNum, Integer pageSize);
    message getMessage(Integer id);

    Response<message> delMessage(Integer id);
    Response<message> insert(message message);
    /*Response<message> update(message message);*/
}
