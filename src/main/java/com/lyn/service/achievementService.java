package com.lyn.service;

import com.github.pagehelper.PageInfo;
import com.lyn.entity.Response;
import com.lyn.entity.achievement;

import java.util.List;

public interface achievementService {
    PageInfo<achievement> getAllAchievement(Integer pageNum, Integer pageSize);
    achievement getAchievement(Integer achieveId);

    Response<achievement> delAchievement(Integer achieveId);
    Response<achievement> insert(achievement achievement);
    Response<achievement> update(achievement achievement);
}
