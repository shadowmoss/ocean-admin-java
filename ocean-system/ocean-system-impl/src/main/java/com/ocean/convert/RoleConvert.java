package com.ocean.convert;

import com.ocean.model.entity.RoleDO;
import com.ocean.model.vo.permission.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ltx
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);
    RoleDO roleCreateVOtoDO(RoleCreateVO roleCreateVO);

    RoleDO roleUpdateVOtoDO(RoleUpdateVO roleUpdateVO);

    RolePageVO RoleDOtoVO(RoleDO item);

    RoleGetOneVO doToGetOneVO(RoleDO roleDO);

    List<RoleListVO> doToListRoleVO(List<RoleDO> roleDOS);
}
