package com.lyn.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyn.dao.messageDao;
import com.lyn.entity.Response;
import com.lyn.entity.message;
import com.lyn.service.messageService;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class messageImpl implements messageService {
    @Resource
    private messageDao messageDao;

    @Override
    public List<message> getAllMessage() {
        return messageDao.getAllMessage();
    }
    @Override
    public PageInfo<message> getAllMessage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<message> message = messageDao.getAllMessage();
        PageInfo<message> pageInfo = new PageInfo<>(message);
        return pageInfo;
    }

    @Override
    public message getMessage(Integer id) {
        return messageDao.getMessage(id);
    }
    @Override
    public Response<message> delMessage(Integer id) {
        Response<message> response=null;
        int num = messageDao.delMessage(id);
        if (num > 0 ){
            response = Response.success("删除成功");
        }else{
            response = Response.fail("删除失败");
        }
        return response;
    }

    @Override
    public Response<message> insert(message message) {
        Response<message> response;
        int num = messageDao.insert(message);
        if (num > 0 ){
            response = Response.success("添加成功");
        }else{
            response = Response.fail("添加失败");
        }
        return response;
    }

    /*@Override
    public Response<message> update(message message) {
        Response<message> response;
        int num = messageDao.update(message);
        if (num > 0){
            response=Response.success("修改成功");
        }else{
            response=Response.fail("修改失败");
        }
        return response;
    }

*/
}
