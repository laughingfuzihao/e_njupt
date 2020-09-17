package com.laughing.e_njupt.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 接单工作者
 * @date 2020/9/11 9:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Worker {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String phone;

    /**
     * 学院
     */
    private String college;
    /**
     * 专业
     */
    private String major;
    /**
     * 梅兰竹菊桃李柳桂荷
     */
    private String address;
    /**
     * 楼号
     */
    private String building;
    /**
     * 宿舍号
     */
    private String room;
    /**
     * 微信号
     */
    private String wechat;

    /**
     * 用户状态
     */
    private Boolean state;


    /**
     * 用户性别
     */
    private String sex;
    /**
     * 跑操接单总天数
     */
    private int workerDay;
    /**
     * 快递单
     */
    private int workerKd;
    /**
     * 外卖单
     */
    private int workerWm;
    /**
     * 累计金额
     */
    private int workMoney;
    /**
     * 需缴纳平台手续费
     */
    private int myMoney;
    /**
     * 实际缴纳
     */
    private int giveMoney;
}
