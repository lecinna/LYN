package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.single;

import java.util.List;

public interface singleService {
   PageInfo<single> getAllSingle(Integer pageNum, Integer pageSize);
   single getSingle(Integer singleId);

   Response<single> delSingle(Integer singleId);
   Response<single> insert(single single);
   Response<single> update(single single);
}
