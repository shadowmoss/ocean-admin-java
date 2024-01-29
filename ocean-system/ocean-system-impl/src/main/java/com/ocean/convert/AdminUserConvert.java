package com.ocean.convert;

import com.ocean.model.entity.AdminUserDO;
import com.ocean.model.vo.AdminUserPageResVO;
import com.ocean.model.vo.AdminUserVO;
import com.ocean.model.vo.auth.AuthenticatedUserVO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author ltx
 */
@Mapper(builder = @Builder(disableBuilder = true),unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminUserConvert {
    AdminUserConvert INSTANCE = Mappers.getMapper(AdminUserConvert.class);
    AdminUserPageResVO convert(AdminUserDO item);

    AdminUserVO DOtoAdminUserVO(AdminUserDO adminUserDO);

    @Mapping(source = "id",target="userId")
    AuthenticatedUserVO DOtoAuthenticatedUserVO(AdminUserDO adminUserDO);
}
