package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.introduce;

import java.util.List;

public interface introduceService {
    //实现分页功能
    PageInfo<introduce> getAllIntroduce(Integer pageNum, Integer pageSize);
    //获取某个事件
    introduce get(Integer introId);
    introduce getIntroduce(introduce introduce);
    //删除事件
    Response<introduce> delIntro(String incident);
    //添加事件
    Response<introduce> insert(introduce introduce);
    //修改事件
    Response<introduce> update(introduce introduce);
}
