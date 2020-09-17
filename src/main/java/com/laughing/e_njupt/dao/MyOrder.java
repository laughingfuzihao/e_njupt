package com.laughing.e_njupt.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 订单类型 1、快递 2、外卖 3、跑操
     */
    private String type;
    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户手机
     */
    private String userphone;
    /**
     * 取货地址
     */
    private String getAddress;

    /**
     * 送货地址
     */
    private String sendAddress;


    /**
     * 货号/外卖手机尾号
     */
    private String code;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 结束日期
     */
    private String endTime;

    /**
     * 接单人
     */
    private String workername;
    private String workerphone;

    /**
     * 订单状态 1、发布状态
     */
    private String states;
    /**
     * 性别要求
     */
    private String sexRequirement;
    /**
     * 跑操天数
     */
    private String days;
    /**
     * 用户登录名
     */
    private String myName;

    /**
     * 接单时间
     */
    private Date getTime;

    /**
     * 预计送达时间
     */
    private Date yjTime;

    /**
     * 实际送达时间
     */
    private Date sdTime;
}
