package com.ocean.convert;


import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.AuthorityMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author ltx
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityConvert {
    public AuthorityConvert INSTANCE = Mappers.getMapper(AuthorityConvert.class);

    AuthorityMenuVO MenuDOtoAuthorityVO(MenuDO item);
}
