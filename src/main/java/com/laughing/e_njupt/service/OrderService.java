package com.laughing.e_njupt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.MyOrder;
import com.laughing.e_njupt.dao.User;
import com.laughing.e_njupt.dao.Worker;
import com.laughing.e_njupt.mapper.OrderMapper;
import com.laughing.e_njupt.mapper.UserMapper;
import com.laughing.e_njupt.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 订单服务
 * @date 2020/9/11 15:53
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private WorkerMapper workerMapper;

    public int addOrder(MyOrder order) {
        return orderMapper.insert(order);
    }


    public List<MyOrder> getAll(String sex) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.like("sex_requirement", sex);
        wrapper.like("states", "待接单");
        wrapper.orderByDesc("create_time");
        return orderMapper.selectList(wrapper);
    }

    /**
     * 用户
     * 我的订单
     */
    public List<MyOrder> getOrderByName(String name) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.like("my_name", name);
        wrapper.orderByDesc("create_time");
        return orderMapper.selectList(wrapper);
    }

    /**
     * 骑手
     * 我的订单
     */
    public List<MyOrder> getOrderWorkByName(String name) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.like("workername", name);
        wrapper.orderByDesc("create_time");
        return orderMapper.selectList(wrapper);
    }

    /**
     * 查询全部
     *
     * @return
     */
    public Page<MyOrder> getAllOrder(int current, int size) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        Page<MyOrder> orderPage = new Page<>(current, size);
        orderPage = orderMapper.selectPage(orderPage, wrapper);
        return orderPage;
    }

    public MyOrder getById(int id) {
        return orderMapper.selectById(id);
    }

    /**
     * 接单
     */
    public void getOrder(int id, String name, String phone,String type) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        MyOrder myOrder = new MyOrder();
        myOrder.setStates("已接单");
        myOrder.setWorkerphone(phone);
        myOrder.setWorkername(name);
        // 接单时间
        Date now = new Date();
        myOrder.setGetTime(now);
        Date afterDate = new Date(now.getTime() + 1800000);
        if (type.equals("跑操")) {
            myOrder.setYjTime(null);
        }else {
            myOrder.setYjTime(afterDate);
        }
        orderMapper.update(myOrder, wrapper);

    }

    /**
     * 送达
     */
    public int sendTo(int id) {
        QueryWrapper<MyOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        MyOrder myOrder = new MyOrder();
        myOrder.setStates("已送达");
        myOrder.setSdTime(new Date());
        return orderMapper.update(myOrder, wrapper);
    }

    /**
     * 入账
     */
    public void workMoney(int workerId, String type, int code, int workkd, int workwm, int workday,
                          int workMoney, int myMoney) {

        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.eq("id", workerId);
        Worker worker = new Worker();

        if (type.equals("快递")) {
            System.out.println("kd");
            worker.setWorkerKd(workkd + 1);
            worker.setWorkMoney(workMoney + 2);
            worker.setMyMoney(myMoney + 1);

            worker.setWorkerWm(workwm);
            worker.setWorkerDay(workday);
        }
        if (type.equals("外卖")) {
            System.out.println("wm");
            worker.setWorkerWm(workwm + 1);
            worker.setWorkMoney(workMoney + 2);
            worker.setMyMoney(myMoney + 1);

            worker.setWorkerKd(workkd);
            worker.setWorkerDay(workday);
        }
        if (type.equals("跑操")) {
            System.out.println("pc");
            worker.setWorkerDay(workday + code);
            worker.setWorkMoney(workMoney + (code * 6));
            worker.setMyMoney(myMoney + (code * 2));

            worker.setWorkerWm(workwm);
            worker.setWorkerKd(workkd);
        }

        workerMapper.update(worker, wrapper);

    }

    /**
     * 录入金额
     */

    public void lr(int id, int m, int workerKd, int workerWm, int workerDay, int workMoney, int myMoney) {

        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Worker worker = new Worker();
        worker.setGiveMoney(m);
        worker.setWorkerDay(workerDay);
        worker.setWorkerKd(workerKd);
        worker.setWorkerWm(workerWm);
        worker.setWorkMoney(workMoney);
        worker.setMyMoney(myMoney);
        workerMapper.update(worker, wrapper);

    }


}
