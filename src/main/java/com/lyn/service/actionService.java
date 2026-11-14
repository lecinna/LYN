package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.action;

import java.util.List;

public interface actionService {
    PageInfo<action> getAllAction(Integer pageNum, Integer pageSize);
    action getAction(Integer actId);

    Response<action> delAction(Integer actId);
    Response<action> insert(action action);
    Response<action> update(action action);
}
