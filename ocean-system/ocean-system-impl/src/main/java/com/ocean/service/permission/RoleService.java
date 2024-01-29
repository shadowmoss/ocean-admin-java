package com.ocean.service.permission;

import com.ocean.common.restful.RestfulPageResult;
import com.ocean.model.vo.permission.*;

import java.util.List;

/**
 * @author ltx
 */
public interface RoleService {
    Long createRole(RoleCreateVO roleCreateVO);

    Boolean updateRole(RoleUpdateVO roleUpdateVO);

    RoleDeleteResultVO deleteRole(Long id);

    RestfulPageResult<RolePageVO> pageRole(RolePageReqVO rolePageReqVO);

    RoleGetOneVO getOneRole(Long id);

    List<RoleListVO> listEnableRole();
}
