package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.singleDao;
import com.lyn.entity.Response;
import com.lyn.entity.single;
import com.lyn.service.singleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("singleService")
public class singleImpl implements singleService {
    @Resource
    private singleDao singleDao;

    @Override
    public PageInfo<single> getAllSingle(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<single> single = singleDao.getAllSingle();
        PageInfo<single> pageInfo = new PageInfo<>(single);
        return pageInfo;
    }
    @Override
    public single getSingle(Integer singleId){
        return singleDao.getSingle(singleId);

    }
    @Override
    public Response<single> delSingle(Integer singleId){
        Response<single> response=null;
        int num = singleDao.delSingle(singleId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }

    @Override
    public Response<single> insert(single single){
        Response<single> response=null;
        int num = singleDao.insert(single);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else {
            response = Response.fail("添加失败");
        }
        return response;
    }

    @Override
    public Response<single> update(single single){
        Response<single> response=null;
        int num = singleDao.update(single);
        if (num > 0 ){
            response = Response.success("修改成功");
        }else {
            response = Response.fail("修改失败");
        }
        return response;
    }
}
