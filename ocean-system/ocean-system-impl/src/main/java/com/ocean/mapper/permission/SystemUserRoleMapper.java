package com.ocean.mapper.permission;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.model.entity.SystemUserRoleDO;
import com.ocean.model.vo.permission.UserBindedRoleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ltx
 */
@Mapper
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRoleDO> {
    List<UserBindedRoleVO> listUserBindedRoles(@Param("id") Long id);

    SystemUserRoleDO selectExist(@Param("roleId")Long roleId, @Param("userId") Long userId);

    void updateDeleted(@Param("oldData") SystemUserRoleDO oldData);
}
