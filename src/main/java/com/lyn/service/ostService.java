package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.ost;

import java.util.List;

public interface ostService {
    PageInfo<ost> getAllOst(Integer pageNum, Integer pageSize);
    ost getOst(Integer ostId);

    Response<ost> delOst(Integer ostId);
    Response<ost> insert(ost ost);
    Response<ost> update(ost ost);
}
