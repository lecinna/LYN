package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.achievementDao;
import com.lyn.entity.Response;
import com.lyn.entity.achievement;
import com.lyn.service.achievementService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("achievementService")
public class achievementServiceImpl implements achievementService {
    @Resource
    private achievementDao achievementDao;
    @Override
    public PageInfo<achievement> getAllAchievement(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<achievement> achievement = achievementDao.getAllAchievement();
        PageInfo<achievement> pageInfo = new PageInfo<>(achievement);
        return pageInfo;
    }

    @Override
    public achievement getAchievement(Integer achieveId) {
        return achievementDao.getAchievement(achieveId);
    }

    @Override
    public Response<achievement> delAchievement(Integer achieveId) {
        Response<achievement> response;
        int num = achievementDao.delAchievement(achieveId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else{
            response = Response.fail("删除失败");
        }
        return response;
    }

    @Override
    public Response<achievement> insert(achievement achievement) {
        Response<achievement> response;
        int num = achievementDao.insert(achievement);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response = Response.fail("添加失败");
        }
        return response;
    }

    @Override
    public Response<achievement> update(achievement achievement) {
        Response<achievement> response;
        int num = achievementDao.update(achievement);
        if (num > 0 ){
            response = Response.success("修改成功");
        }else{
            response = Response.fail("修改失败");
        }
        return response;
    }
}
