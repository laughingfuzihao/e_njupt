package com.laughing.e_njupt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.User;
import com.laughing.e_njupt.dao.Worker;
import com.laughing.e_njupt.mapper.WorkerMapper;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/14 10:52
 */
@Service
public class WorkerService {
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private Worker worker;

    /**
     * 单条新增Worker
     *
     * @param worker
     * @return
     */
    public int addWorker(Worker worker) {
        return workerMapper.insert(worker);
    }

    /**
     * worker信息修改
     * @param worker
     * @return
     */
    public int updateUser(Worker worker){
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.eq("username", worker.getUsername());
        return workerMapper.update(worker, wrapper);
    }


    /**
     * 查询全部
     *
     * @return
     */
    public Page<Worker> getAllWorker(int current, int size) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        Page<Worker> workerPage = new Page<>(current, size);
        workerPage = workerMapper.selectPage(workerPage, wrapper);
        return workerPage;
    }

    public void turnOn(int id) {
        worker.setId(id);
        worker.setState(true);
        workerMapper.updateById(worker);
    }


    public void turnOff(int id) {
        worker.setId(id);
        worker.setState(false);
        workerMapper.updateById(worker);
    }


    public int checkWorker(String username, String password) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        if (!StringUtil.isBlank(password)) {
            wrapper.eq("password", password);
        }
        wrapper.eq("state", true);
        List<Worker> workerList = workerMapper.selectList(wrapper);
        return workerList.size();
    }

    /**
     * 性别查询
     * @param username
     * @return
     */
    public String getSex(String username) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        List<Worker> workerList = workerMapper.selectList(wrapper);
        return workerList.get(0).getSex();
    }


    /**
     * 手机查询
     * @param username
     * @return
     */
    public String getPhone(String username) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        List<Worker> workerList = workerMapper.selectList(wrapper);
        return workerList.get(0).getPhone();
    }

    /**
     * 手机ID
     * @param username
     * @return
     */
    public Worker getMyId(String username) {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        List<Worker> workerList = workerMapper.selectList(wrapper);
        return workerList.get(0);
    }



    public int checkHaveUser(String username){
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        List<Worker> userList = workerMapper.selectList(wrapper);
        return userList.size();
    }

}
