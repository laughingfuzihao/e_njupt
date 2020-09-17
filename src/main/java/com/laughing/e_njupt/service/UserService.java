package com.laughing.e_njupt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.User;
import com.laughing.e_njupt.dao.Worker;
import com.laughing.e_njupt.mapper.UserMapper;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 9:23
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 单条新增用户
     * @param user
     * @return
     */
    public int addUser(User user){
       return userMapper.insert(user);
    }



    /**
     * 查询全部
     * @return
     */
    public Page<User> getAllUser(int current, int size){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Page<User> userPage = new Page<>(current, size);
        userPage =  userMapper.selectPage(userPage, wrapper);
        return userPage;
    }



    /**
     * 用户信息修改
     * @param user
     * @return
     */
    public int updateUser(User user){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        return userMapper.update(user, wrapper);
    }


    public int checkUser(String username,String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        if (!StringUtil.isBlank(password)) {
            wrapper.eq("password", password);
        }
        List<User> userList = userMapper.selectList(wrapper);
        return userList.size();
    }

    public int checkHaveUser(String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (!StringUtil.isBlank(username)) {
            wrapper.eq("username", username);
        }
        List<User> userList = userMapper.selectList(wrapper);
        return userList.size();
    }


}
