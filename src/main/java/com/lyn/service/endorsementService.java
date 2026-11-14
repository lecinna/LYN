package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.endorsement;

import java.util.List;

public interface endorsementService {
    PageInfo<endorsement> getAllEndorsement(Integer pageNum, Integer pageSize);
    endorsement getEndorsement(Integer endId);

    Response<endorsement> delEndorsement(Integer endorseId);
    Response<endorsement> insert(endorsement endorsement);
    Response<endorsement> update(endorsement endorsement);
}
