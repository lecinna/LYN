package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.performanceDao;
import com.lyn.entity.Response;
import com.lyn.entity.performance;
import com.lyn.service.performanceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("performanceService")
public class PerformanceImpl implements performanceService {
    @Resource
    private performanceDao performanceDao;
    @Override
    public PageInfo<performance> getAllPerformance(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<performance> performance = performanceDao.getAllPerformance();
        PageInfo<performance> pageInfo = new PageInfo<>(performance);
        return pageInfo;
    }
    @Override
    public performance getPer(Integer perId){
      return performanceDao.getPer(perId);
    }

    @Override
    public Response<performance> delPer(Integer perId){
        Response<performance> response=null;
        int num = performanceDao.delPer(perId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }
    @Override
    public Response<performance> insert(performance performance){
        Response<performance> response;
        int num = performanceDao.insert(performance);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response = Response.fail("添加失败");
        }
        return response;
    }
    @Override
    public Response<performance> update(performance performance){
        Response<performance> response;
        int num = performanceDao.update(performance);
        if (num > 0){
            response=Response.success("修改成功");
        }else{
            response=Response.fail("修改失败");
        }
        return response;
    }
}
