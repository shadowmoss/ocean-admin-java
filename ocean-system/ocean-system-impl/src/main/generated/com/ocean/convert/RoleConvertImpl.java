package com.ocean.convert;

import com.ocean.model.entity.RoleDO;
import com.ocean.model.vo.permission.RoleCreateVO;
import com.ocean.model.vo.permission.RoleGetOneVO;
import com.ocean.model.vo.permission.RoleListVO;
import com.ocean.model.vo.permission.RolePageVO;
import com.ocean.model.vo.permission.RoleUpdateVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-29T22:12:37+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class RoleConvertImpl implements RoleConvert {

    @Override
    public RoleDO roleCreateVOtoDO(RoleCreateVO roleCreateVO) {
        if ( roleCreateVO == null ) {
            return null;
        }

        RoleDO.RoleDOBuilder roleDO = RoleDO.builder();

        roleDO.name( roleCreateVO.getName() );
        roleDO.code( roleCreateVO.getCode() );
        roleDO.roleOrder( roleCreateVO.getRoleOrder() );
        roleDO.status( roleCreateVO.getStatus() );
        roleDO.remark( roleCreateVO.getRemark() );

        return roleDO.build();
    }

    @Override
    public RoleDO roleUpdateVOtoDO(RoleUpdateVO roleUpdateVO) {
        if ( roleUpdateVO == null ) {
            return null;
        }

        RoleDO.RoleDOBuilder roleDO = RoleDO.builder();

        roleDO.id( roleUpdateVO.getId() );
        roleDO.name( roleUpdateVO.getName() );
        roleDO.code( roleUpdateVO.getCode() );
        roleDO.roleOrder( roleUpdateVO.getRoleOrder() );
        roleDO.status( roleUpdateVO.getStatus() );
        roleDO.remark( roleUpdateVO.getRemark() );

        return roleDO.build();
    }

    @Override
    public RolePageVO RoleDOtoVO(RoleDO item) {
        if ( item == null ) {
            return null;
        }

        RolePageVO rolePageVO = new RolePageVO();

        rolePageVO.setId( item.getId() );
        rolePageVO.setName( item.getName() );
        rolePageVO.setRoleType( item.getRoleType() );
        rolePageVO.setCode( item.getCode() );
        rolePageVO.setRoleOrder( item.getRoleOrder() );
        rolePageVO.setRemark( item.getRemark() );
        rolePageVO.setStatus( item.getStatus() );
        rolePageVO.setCreateTime( item.getCreateTime() );

        return rolePageVO;
    }

    @Override
    public RoleGetOneVO doToGetOneVO(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleGetOneVO roleGetOneVO = new RoleGetOneVO();

        roleGetOneVO.setId( roleDO.getId() );
        roleGetOneVO.setName( roleDO.getName() );
        roleGetOneVO.setCode( roleDO.getCode() );
        roleGetOneVO.setRoleOrder( roleDO.getRoleOrder() );
        roleGetOneVO.setStatus( roleDO.getStatus() );
        roleGetOneVO.setRemark( roleDO.getRemark() );

        return roleGetOneVO;
    }

    @Override
    public List<RoleListVO> doToListRoleVO(List<RoleDO> roleDOS) {
        if ( roleDOS == null ) {
            return null;
        }

        List<RoleListVO> list = new ArrayList<RoleListVO>( roleDOS.size() );
        for ( RoleDO roleDO : roleDOS ) {
            list.add( roleDOToRoleListVO( roleDO ) );
        }

        return list;
    }

    protected RoleListVO roleDOToRoleListVO(RoleDO roleDO) {
        if ( roleDO == null ) {
            return null;
        }

        RoleListVO roleListVO = new RoleListVO();

        roleListVO.setId( roleDO.getId() );
        roleListVO.setName( roleDO.getName() );
        roleListVO.setCode( roleDO.getCode() );
        roleListVO.setStatus( roleDO.getStatus() );
        roleListVO.setRoleType( roleDO.getRoleType() );

        return roleListVO;
    }
}
