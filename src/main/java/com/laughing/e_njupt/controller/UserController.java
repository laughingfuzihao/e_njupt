package com.laughing.e_njupt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.User;
import com.laughing.e_njupt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 9:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/add")
    public int addUser(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "phone") String phone,
                       @RequestParam(value = "address") String address,
                       @RequestParam(value = "college") String college,
                       @RequestParam(value = "building") String building,
                       @RequestParam(value = "room") String room,
                       @RequestParam(value = "major") String major) {

        if (userService.checkHaveUser(username) == 1) {
            // 用户已存在
            return 4;
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setAddress(address);
            user.setCollege(college);
            user.setMajor(major);
            user.setRoom(room);
            user.setBuilding(building);
            return userService.addUser(user);
        }

    }

    /**
     * 用户信息修改
     */
    @PostMapping("/update")
    public int updateUser(@RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "phone") String phone,
                          @RequestParam(value = "address") String address,
                          @RequestParam(value = "college") String college,
                          @RequestParam(value = "building") String building,
                          @RequestParam(value = "room") String room,
                          @RequestParam(value = "major") String major) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setCollege(college);
        user.setMajor(major);
        user.setRoom(room);
        user.setBuilding(building);
        return userService.updateUser(user);
    }


    /**
     * 用户查询
     *
     * @return https://laughing-blog.cn/user/getAllUser/1/5
     */
    @GetMapping("/getAllUser/{current}/{size}")
    public Page<User> getAllUser(@PathVariable("current") int current,
                                 @PathVariable("size") int size) {
        return userService.getAllUser(current, size);

    }

    /**
     * 用户登录校验
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/check")
    public int checkUser(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {


        return userService.checkUser(username, password);

    }


}
