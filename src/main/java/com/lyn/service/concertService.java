package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.concert;
import com.lyn.entity.Response;

import java.util.List;

public interface concertService {
        // 获取所有演唱会
        PageInfo<concert> getAllConcert(int pageNum, int pageSize);

        // 获取即将开始的演唱会
       /* List<concert> getUpcoming();*/

        // 根据ID查询
        concert getById(Integer id);

        // 添加演唱会
        Response<concert> insert(concert concert);

        // 修改演唱会
        Response<concert> update(concert concert);

        // 删除演唱会
        Response<concert> delConcert(String title);
    }