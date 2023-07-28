package com.ocean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ltx
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
