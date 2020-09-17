package com.laughing.e_njupt.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description: 用户
 * @date 2020/9/11 9:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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


}
