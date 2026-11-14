package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.endorsementDao;
import com.lyn.entity.Response;
import com.lyn.entity.endorsement;
import com.lyn.service.endorsementService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("endorsementService")
public class endorsementImpl implements endorsementService {
    @Resource
    private endorsementDao endorsementDao;
    @Override
    public PageInfo<endorsement> getAllEndorsement(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<endorsement> endorsement = endorsementDao.getAllEndorsement();
        PageInfo<endorsement> pageInfo = new PageInfo<>(endorsement);
        return pageInfo;
    }

    @Override
    public endorsement getEndorsement(Integer endorseId) {
        return endorsementDao.getEndorsement(endorseId);
    }
    @Override
    public Response<endorsement> delEndorsement(Integer endorseId) {
        Response<endorsement> response;
        int num = endorsementDao.delEndorsement(endorseId);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else{
            response = Response.fail("删除失败");
        }
        return response;
    }

    @Override
    public Response<endorsement> insert(endorsement endorsement) {
        Response<endorsement> response;
        int num = endorsementDao.insert(endorsement);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response = Response.fail("添加失败");
        }
        return response;
    }

    @Override
    public Response<endorsement> update(endorsement endorsement) {
        Response<endorsement> response;
        int num = endorsementDao.update(endorsement);
        if (num > 0 ){
            response = Response.success("修改成功");
        }else{
            response = Response.fail("修改失败");
        }
        return response;
    }
}
