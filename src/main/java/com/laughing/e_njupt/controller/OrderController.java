package com.laughing.e_njupt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughing.e_njupt.dao.MyOrder;
import com.laughing.e_njupt.dao.User;
import com.laughing.e_njupt.dao.Worker;
import com.laughing.e_njupt.service.OrderService;
import com.laughing.e_njupt.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 15:55
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private WorkerService workerService;

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    /**
     * 添加取快递订单
     *
     * @return
     */
    @PostMapping("/add/kd")
    public int addKDOrder(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "userphone") String userphone,
            @RequestParam(value = "code") String code,
            @RequestParam(value = "getAddress") String getAddress,
            @RequestParam(value = "sendAddress") String sendAddress,
            @RequestParam(value = "sexRequirement") String sexRequirement,
            // 用户登陆名
            @RequestParam(value = "myName") String myName,
            @RequestParam(value = "endTime") String endTime) throws ParseException {

        MyOrder order = new MyOrder();
        order.setStates("待接单");
        order.setType("快递");
        order.setUsername(username);
        order.setUserphone(userphone);
        order.setGetAddress(getAddress);
        order.setSendAddress(sendAddress);
        order.setCode(code);
        order.setCreateTime(new Date());
        order.setEndTime(endTime);
        order.setSexRequirement(sexRequirement);
        order.setMyName(myName);
        return orderService.addOrder(order);
    }


    /**
     * 添加取外卖订单
     *
     * @return
     */
    @PostMapping("/add/wm")
    public int addWMOrder(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "userphone") String userphone,
            @RequestParam(value = "code") String code,
            // 用户登陆名
            @RequestParam(value = "myName") String myName,
            @RequestParam(value = "getAddress") String getAddress,
            @RequestParam(value = "sendAddress") String sendAddress,
            @RequestParam(value = "sexRequirement") String sexRequirement) {
        MyOrder order = new MyOrder();
        order.setMyName(myName);
        order.setStates("待接单");
        order.setType("外卖");
        order.setUsername(username);
        order.setUserphone(userphone);
        order.setGetAddress(getAddress);
        order.setSendAddress(sendAddress);
        order.setCode(code);
        Date nowDate = new Date();
        order.setCreateTime(nowDate);
        // 默认截止时间为15分
        Date afterDate = new Date(nowDate.getTime() + 900000);
        order.setEndTime(sdf.format(afterDate));
        order.setSexRequirement(sexRequirement);
        return orderService.addOrder(order);
    }


    /**
     * 添加跑操订单
     *
     * @return
     */
    @PostMapping("/add/pc")
    public int addPCOrder(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "userphone") String userphone,
            @RequestParam(value = "code") String code,
            // 用户登陆名
            @RequestParam(value = "myName") String myName,
            @RequestParam(value = "sendAddress") String sendAddress) {
        MyOrder order = new MyOrder();
        order.setStates("待接单");
        order.setMyName(myName);
        order.setType("跑操");
        order.setGetAddress("骑手取卡");
        order.setUsername(username);
        order.setUserphone(userphone);
        order.setSendAddress(sendAddress);
        order.setCode(code);
        order.setSexRequirement("男女");
        order.setCreateTime(new Date());
        return orderService.addOrder(order);
    }

    /**
     * 性别展示订单列表
     *
     * @return
     */
    @GetMapping("/getAll/{name}")
    public List<MyOrder> getAll(@PathVariable("name") String name) throws ParseException {
        String sex = workerService.getSex(name);
        List<MyOrder> orderList = orderService.getAll(sex);

        for (int i = 0; i < orderList.size(); i++) {
            if ("待接单".equals(orderList.get(i).getStates())) {
                String endTime = orderList.get(i).getEndTime();
                String nowTime = sdf.format(new Date());
                if (endTime != null && endTime != "") {
                    Date end = sdf.parse(endTime);
                    Date now = sdf.parse(nowTime);
                    if (now.after(end)) {
                        orderList.remove(i);
                    }
                }
            }
        }
        return orderList;

    }


    /**
     * 订单查询
     *
     * @return
     */
    @GetMapping("/getOrder/{current}/{size}")
    public Page<MyOrder> getAllUser(@PathVariable("current") int current,
                                    @PathVariable("size") int size) {
        return orderService.getAllOrder(current, size);

    }

    /**
     * 接单并返回
     *
     * @param id
     * @return
     */
    @GetMapping("/getbyid/{id}/{name}")
    public MyOrder getById(@PathVariable("id") int id,
                           @PathVariable("name") String name) {
        String states = orderService.getById(id).getStates();

        if (states.equals("待接单")) {
            String type = orderService.getById(id).getType();
            orderService.getOrder(id, name, workerService.getPhone(name), type);
        }


        return orderService.getById(id);

    }

    /**
     * 用户 我的订单 我成功在、搭载微内核形式化验证且兼容安卓的分布式云AI操作系统鸿蒙OS的荣耀增智慧屏
     *
     * @param name
     * @return
     */
    @GetMapping("/getorderbyname/{name}")
    public List<MyOrder> getOrderByName(@PathVariable("name") String name) throws ParseException {
        List<MyOrder> orderList = orderService.getOrderByName(name);
        for (int i = 0; i < orderList.size(); i++) {
            if ("待接单".equals(orderList.get(i).getStates())) {
                String endTime = orderList.get(i).getEndTime();
                String nowTime = sdf.format(new Date());
                if (endTime != null && endTime != "") {
                    Date end = sdf.parse(endTime);
                    Date now = sdf.parse(nowTime);
                    if (now.after(end)) {
                        System.out.println("超时取消");
                        orderList.get(i).setStates("超时取消");
                    }
                }
            }
        }
        return orderList;
    }

    /**
     * 用户 我的订单详情
     *
     * @param id
     * @return
     */

    @GetMapping("/usergetbyid/{id}")
    public MyOrder getUserMyOrder(@PathVariable("id") int id) throws ParseException {
        MyOrder order = orderService.getById(id);
        if ("待接单".equals(order.getStates())) {
            String endTime = order.getEndTime();
            String nowTime = sdf.format(new Date());
            if (endTime != null && endTime != "") {
                Date end = sdf.parse(endTime);
                Date now = sdf.parse(nowTime);
                if (now.after(end)) {
                    order.setStates("超时取消");
                }
            }
        }

        return order;
    }

    /**
     * 骑手 我的订单
     *
     * @param name
     * @return
     */
    @GetMapping("/getorderbynamework/{name}")
    public List<MyOrder> getOrderByWorkName(@PathVariable("name") String name) throws ParseException {
        List<MyOrder> orderList = orderService.getOrderWorkByName(name);


        return orderList;
    }

    /**
     * 送达
     *
     * @param id
     * @return
     */
    @GetMapping("/sendto/{id}")
    public int sendTo(@PathVariable("id") int id) {


        MyOrder myOrder = orderService.getById(id);
        // myOrder.getStates() 已送达
        if (!myOrder.getStates().equals("已送达")) {
            String workername = myOrder.getWorkername();
            String type = myOrder.getType();
            String code = myOrder.getCode();
            Worker worker = workerService.getMyId(workername);
            orderService.workMoney(worker.getId(), type, Integer.valueOf(code), worker.getWorkerKd(), worker.getWorkerWm(), worker.getWorkerDay(), worker.getWorkMoney(), worker.getMyMoney());
            return orderService.sendTo(id);
        }
        return 1;

    }

    //   url: "https://laughing-blog.cn/order/lr/" + row.id + "/"+m + "/"+row.workerKd+ "/"+row.workerWm+ "/"+row.workerDay+ "/"+row.workMoney+ "/"+row.myMoney,
    @GetMapping("/lr/{id}/{m}/{workerKd}/{workerWm}/{workerDay}/{workMoney}/{myMoney}")
    public void lr(@PathVariable("id") int id,
                   @PathVariable("m") int m,
                   @PathVariable("workerKd") int workerKd,
                   @PathVariable("workerWm") int workerWm,
                   @PathVariable("workerDay") int workerDay,
                   @PathVariable("workMoney") int workMoney,
                   @PathVariable("myMoney") int myMoney) {
        orderService.lr(id, m, workerKd, workerWm, workerDay, workMoney, myMoney);

    }


}
