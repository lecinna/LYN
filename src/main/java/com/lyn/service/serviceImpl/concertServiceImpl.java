package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.concertDao;
import com.lyn.entity.Response;
import com.lyn.entity.concert;
import com.lyn.service.concertService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("concertService")
public class concertServiceImpl implements concertService {
    @Resource
    private com.lyn.dao.concertDao concertDao;
        /*@Override
    public List<concert> getUpcoming() {
        return concertDao.getUpcoming();
    }*/

    @Override
    public PageInfo<concert> getAllConcert(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<concert> concerts = concertDao.getAllConcert();
        PageInfo<concert> pageInfo = new PageInfo<>(concerts);
        return pageInfo;
    }

    @Override
    public concert getById(Integer id) {
        return concertDao.getById(id);
    }

    @Override
    public Response<concert> insert(concert concert) {
        Response<concert> res = null;

        if (concert.getTitle() == null || concert.getTitle().isEmpty()) {
            return Response.fail("演唱会标题不能为空");
        }
        if (concert.getDate() == null) {
            return Response.fail("演出日期不能为空");
        }

        // 默认状态：1-即将开演
        if (concert.getStatus() == null) {
            concert.setStatus(1);
        }

        int num = concertDao.insert(concert);
        if (num > 0) {
            res = Response.success("添加成功");
        } else {
            res = Response.fail("添加失败");
        }
        return res;
    }

    @Override
    public Response<concert> update(concert concert) {
        Response<concert> res = null;

        if (concert.getId() == null) {
            return Response.fail("ID不能为空");
        }

        int num = concertDao.update(concert);
        if (num > 0) {
            res = Response.success("修改成功");
        } else {
            res = Response.fail("修改失败");
        }
        return res;
    }

    @Override
    public Response<concert> delConcert(String title) {
        Response<concert> res = null;

        if (title == null) {
            return Response.fail("ID不能为空");
        }

        int num = concertDao.delete(title);
        if (num > 0) {
            res = Response.success("删除成功");
        } else {
            res = Response.fail("删除失败");
        }
        return res;
    }
}

