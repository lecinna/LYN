package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.performance;

import java.util.List;

public interface performanceService {
    PageInfo<performance> getAllPerformance(Integer pageNum, Integer pageSize);
    performance getPer(Integer perId);

    Response<performance> delPer(Integer perID);
    Response<performance> insert(performance performance);
    Response<performance> update(performance performance);
}
