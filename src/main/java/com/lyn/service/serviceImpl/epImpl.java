package com.lyn.service.serviceImpl;

import com.lyn.dao.epDao;
import com.lyn.entity.Response;
import com.lyn.entity.ep;
import com.lyn.service.epService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("epService")
public class epImpl implements epService {
    @Resource
    private epDao epDao;

    @Override
    public List<ep> getAllEp() {
        return epDao.getAllEp();
    }
    @Override
    public ep getEp(Integer epId) {
        return epDao.getEp(epId);
    }
    @Override
    public Response<ep> delEp(Integer eId) {
        Response<ep> response=null;
        int num = epDao.delEp(eId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else {
            response = Response.fail("删除失败");
        }
        return response;
    }
    @Override
    public Response<ep> insert(ep ep) {
        Response<ep> response;
        int num = epDao.insert(ep);
        if (num > 0 ){
            response=Response.success("添加成功");
        }else{
            response=Response.fail("添加失败");
        }
        return response;
    }
    @Override
    public Response<ep> update(ep ep) {
        Response<ep> response;
        int num = epDao.update(ep);
        if (num > 0){
            response=Response.success("修改成功");
        }else{
            response=Response.fail("修改失败");
        }
        return response;
    }
}
