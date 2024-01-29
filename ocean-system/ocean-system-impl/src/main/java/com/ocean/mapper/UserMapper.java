package com.ocean.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.model.entity.AdminUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ltx
 */
@Mapper
public interface UserMapper extends BaseMapper<AdminUserDO> {
}
