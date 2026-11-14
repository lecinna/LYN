package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.actionDao;
import com.lyn.entity.Response;
import com.lyn.entity.action;
import com.lyn.service.actionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service("actionService")
public class actionImpl implements actionService {
    @Resource
    private actionDao actionDao;
    @Override
    public PageInfo<action> getAllAction(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<action> action = actionDao.getAllAction();
        PageInfo<action> pageInfo = new PageInfo<>(action);
        return pageInfo;
    }
    @Override
    public action getAction(Integer actId) {
        return actionDao.getAction(actId);
    }
    @Override
    public Response<action> delAction(Integer actId) {
        Response<action> response=null;
        int num = actionDao.delAction(actId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }
    @Override
    public Response<action> insert(action action) {
        Response<action> response;
        int num = actionDao.insert(action);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response=Response.fail("添加失败");
        }
        return response;
    }
    @Override
    public Response<action> update(action action) {
        Response<action> response;
        int num = actionDao.update(action);
        if (num > 0){
            response=Response.success("修改成功");
        }else{
            response=Response.fail("修改失败");
        }
        return response;
    }
}
