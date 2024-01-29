package com.ocean.convert;

import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author ltx
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConvert {
    public MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

    MenuDO createVOtoDO(MenuCreateVO menuCreateVO);

    MenuDO updateVOtoDO(MenuUpdateVO menuUpdateVO);

    MenuResVO DOtoListVO(MenuDO item);

    MenuBaseVO DOtoBaseVO(MenuDO menuDO);

    MenuGetOneVO DOtoGetOneVO(MenuDO menuDO);
}
