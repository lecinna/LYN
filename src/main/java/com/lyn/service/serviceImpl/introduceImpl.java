package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.introduceDao;
import com.lyn.entity.Response;
import com.lyn.entity.introduce;
import com.lyn.service.introduceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("introduceService")
public class introduceImpl implements introduceService {
    @Resource
    private introduceDao introduceDao;

    @Override
    public PageInfo<introduce> getAllIntroduce(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<introduce> introduce = introduceDao.getAllIntroduce();
        PageInfo<introduce> pageInfo = new PageInfo<>(introduce);
        return pageInfo;
    }
    @Override
    public introduce get(Integer introId) {
        return introduceDao.get(introId);
    }
    @Override
    public introduce getIntroduce(introduce introduce) {
        return introduceDao.getIntroduce(introduce);
    }
    @Override
    public Response<introduce> delIntro(String incident ) {
        Response<introduce> res = null;
        int num = introduceDao.delIntro(incident);
        if (num > 0) {
            res = Response.success("删除成功");
        } else {
            res = Response.fail("删除失败");
        }
        return res;
    }

    @Override
    public Response<introduce> insert(introduce introduce) {
        Response<introduce> res;
        if (introduce.getIncident() == null) {
            res = Response.fail("事件不能为空");
            return res;
        }
        int num = introduceDao.insert(introduce);
        if (num > 0) {
            res = Response.success("添加成功");
        } else {
            res = Response.fail("添加失败");
        }
        return res;
    }
    @Override
   public Response<introduce> update(introduce introduce) {
        Response<introduce> res;
        if (introduce.getIncident() == null) {
            res = Response.fail("事件不能为空");
            return res;
        }
        int num = introduceDao.update(introduce);
        if (num > 0) {
            res = Response.success("修改成功");
        } else {
            res = Response.fail("修改失败");
        }
        return res;
    }
}
