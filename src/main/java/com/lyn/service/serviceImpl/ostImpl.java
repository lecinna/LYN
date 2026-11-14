package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.ostDao;
import com.lyn.entity.Response;
import com.lyn.entity.ost;
import com.lyn.service.ostService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ostService")
public class ostImpl implements ostService {
    @Resource
    private ostDao ostDao;

    @Override
    public PageInfo<ost> getAllOst(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ost> ost = ostDao.getAllOst();
        PageInfo<ost> pageInfo = new PageInfo<>(ost);
        return pageInfo;
    }
    @Override
    public ost getOst(Integer oID){
        return ostDao.getOst(oID);
    }
    @Override
    public Response<ost> delOst(Integer oId){
        Response<ost> response=null;
        int num = ostDao.delOst(oId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }
    @Override
    public Response<ost> insert(ost ost){
        Response<ost> response;
        int num = ostDao.insert(ost);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response = Response.fail("添加失败");
        }
        return response;
    }
    @Override
    public Response<ost> update(ost ost){
        Response<ost> response;
        int num = ostDao.update(ost);
        if (num > 0 ){
            response = Response.success("修改成功");
        }else{
            response = Response.fail("修改失败");
        }
        return response;
    }
}
