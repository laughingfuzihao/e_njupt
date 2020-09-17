package com.laughing.e_njupt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.Worker;
import com.laughing.e_njupt.mapper.OrderMapper;
import com.laughing.e_njupt.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/14 10:52
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private OrderMapper orderMapper;


    /**
     * worker注册
     */
    @PostMapping("/add")
    public int addWorker(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "phone") String phone,
                         @RequestParam(value = "address") String address,
                         @RequestParam(value = "college") String college,
                         @RequestParam(value = "building") String building,
                         @RequestParam(value = "room") String room,
                         @RequestParam(value = "wechat") String wechat,
                         @RequestParam(value = "sex") String sex,
                         @RequestParam(value = "major") String major) {
        if (workerService.checkHaveUser(username) == 1) {
            // 用户已存在
            return 4;
        } else {
            Worker worker = new Worker();
            worker.setUsername(username);
            worker.setPassword(password);
            worker.setPhone(phone);
            worker.setAddress(address);
            worker.setCollege(college);
            worker.setMajor(major);
            worker.setRoom(room);
            worker.setSex(sex);
            worker.setBuilding(building);
            worker.setWechat(wechat);
            worker.setState(false);
            worker.setWorkMoney(0);
            worker.setMyMoney(0);
            worker.setGiveMoney(0);
            worker.setWorkerDay(0);
            worker.setWorkerWm(0);
            worker.setWorkerKd(0);
            return workerService.addWorker(worker);
        }
    }


    /**
     * worker信息修改
     */
    @PostMapping("/update")
    public int updateWorker(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "address") String address,
                            @RequestParam(value = "college") String college,
                            @RequestParam(value = "building") String building,
                            @RequestParam(value = "room") String room,
                            @RequestParam(value = "wechat") String wechat,
                            @RequestParam(value = "sex") String sex,
                            @RequestParam(value = "major") String major) {

        Worker worker = new Worker();
        worker.setUsername(username);
        worker.setPassword(password);
        worker.setPhone(phone);
        worker.setAddress(address);
        worker.setCollege(college);
        worker.setMajor(major);
        worker.setRoom(room);
        worker.setSex(sex);
        worker.setBuilding(building);
        worker.setWechat(wechat);
        worker.setState(true);
        worker.setWorkMoney(workerService.getMyId(username).getWorkMoney());
        worker.setMyMoney(workerService.getMyId(username).getMyMoney());
        worker.setGiveMoney(workerService.getMyId(username).getGiveMoney());
        worker.setWorkerDay(workerService.getMyId(username).getWorkerDay());
        worker.setWorkerWm(workerService.getMyId(username).getWorkerWm());
        worker.setWorkerKd(workerService.getMyId(username).getWorkerKd());
        return workerService.updateUser(worker);

    }

    /**
     * worker 查询金额
     *
     * @return
     */
    @GetMapping("/getM/{name}")
    public Worker getM(@PathVariable("name") String name) {
        return workerService.getMyId(name);
    }


    /**
     * worker查询
     *
     * @return
     */
    @GetMapping("/getAllUser/{current}/{size}")
    public Page<Worker> getAllUser(@PathVariable("current") int current,
                                   @PathVariable("size") int size) {
        return workerService.getAllWorker(current, size);
    }

    /**
     * 审核通过
     *
     * @param id
     */
    @GetMapping("/turn/on/{id}")
    public void turnOn(@PathVariable("id") int id) {
        workerService.turnOn(id);
    }

    /**
     * 审核不通过
     *
     * @param id
     */
    @GetMapping("/turn/off/{id}")
    public void turnOff(@PathVariable("id") int id) {
        workerService.turnOff(id);
    }

    /**
     * worker登录校验
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/check")
    public int checkWorker(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password) {


        return workerService.checkWorker(username, password);

    }
}
