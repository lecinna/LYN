package com.lyn.service;

import com.lyn.entity.Response;
import com.lyn.entity.ep;

import java.util.List;

public interface epService {
    List<ep> getAllEp();
    ep getEp(Integer eId);
    Response<ep> delEp(Integer eId);
    Response<ep> insert(ep ep);
    Response<ep> update(ep ep);
}
