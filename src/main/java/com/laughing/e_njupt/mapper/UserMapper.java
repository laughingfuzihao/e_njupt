package com.laughing.e_njupt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughing.e_njupt.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 2020/9/11 9:22
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
