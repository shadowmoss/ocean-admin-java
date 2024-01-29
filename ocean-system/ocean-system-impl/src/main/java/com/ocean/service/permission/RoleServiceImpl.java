package com.ocean.service.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.restful.RestfulPageResult;
import com.ocean.convert.RoleConvert;
import com.ocean.enums.RoleStatusEnum;
import com.ocean.enums.RoleTypeEnum;
import com.ocean.mapper.permission.RoleMapper;
import com.ocean.model.entity.RoleDO;
import com.ocean.model.vo.permission.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ltx
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Resource
    private RoleMapper roleMapper;
    @Override
    public Long createRole(RoleCreateVO roleCreateVO) {
        RoleDO roleDO = RoleConvert.INSTANCE.roleCreateVOtoDO(roleCreateVO);
        roleDO.setRoleType(RoleTypeEnum.CUSTOM_ROLE.type);
        roleMapper.insert(roleDO);
        return roleDO.getId();
    }

    @Override
    public Boolean updateRole(RoleUpdateVO roleUpdateVO) {
        RoleDO roleDO = RoleConvert.INSTANCE.roleUpdateVOtoDO(roleUpdateVO);
        int flag = roleMapper.updateById(roleDO);
        if(flag>0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public RoleDeleteResultVO deleteRole(Long id) {
        LambdaQueryWrapper<RoleDO> roleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleDOLambdaQueryWrapper.eq(RoleDO::getId,id);
        RoleDO roleDO = roleMapper.selectOne(roleDOLambdaQueryWrapper);
        RoleDeleteResultVO roleDeleteResultVO =  new RoleDeleteResultVO();
        if(roleDO.getRoleType().equals(RoleTypeEnum.SYSTEM_INNER_ROLE.type)){
            roleDeleteResultVO.setMsg("该角色为系统内置角色无法删除");
            roleDeleteResultVO.setFlag(false);
            return roleDeleteResultVO;
        }
        roleMapper.deleteById(id);
        roleDeleteResultVO.setMsg("删除成功");
        roleDeleteResultVO.setFlag(true);
        return roleDeleteResultVO;
    }

    @Override
    public RestfulPageResult<RolePageVO> pageRole(RolePageReqVO rolePageReqVO) {
        IPage<RoleDO> rolePage = new Page<>(rolePageReqVO.getCurrentPageNo(),rolePageReqVO.getPageSize());
        LambdaQueryWrapper<RoleDO> roleDOLambdaQueryWrapper = new LambdaQueryWrapper<RoleDO>();
        if(!Objects.isNull(rolePageReqVO.getName())){
            roleDOLambdaQueryWrapper.like(RoleDO::getName,rolePageReqVO.getName());
            roleDOLambdaQueryWrapper.or();
        }
        if(!Objects.isNull(rolePageReqVO.getCode())){
            roleDOLambdaQueryWrapper.like(RoleDO::getCode,rolePageReqVO.getCode());
            roleDOLambdaQueryWrapper.or();
        }
        if(!Objects.isNull(rolePageReqVO.getStatus())){
            roleDOLambdaQueryWrapper.like(RoleDO::getStatus,rolePageReqVO.getStatus());
            roleDOLambdaQueryWrapper.or();
        }
        if(!Objects.isNull(rolePageReqVO.getStartTime()) && !Objects.isNull(rolePageReqVO.getEndTime())){
            roleDOLambdaQueryWrapper.between(RoleDO::getCreateTime,rolePageReqVO.getStartTime(), rolePageReqVO.getEndTime());
        }
        IPage<RoleDO> roleDOIPage = roleMapper.selectPage(rolePage, roleDOLambdaQueryWrapper);
        RestfulPageResult<RolePageVO> result = new RestfulPageResult<>();
        result.setCurrentPageNo(roleDOIPage.getCurrent());
        result.setTotal(roleDOIPage.getTotal());
        result.setSize(roleDOIPage.getSize());
        List<RolePageVO> records = new ArrayList<>();
        for(RoleDO item : roleDOIPage.getRecords()){
            RolePageVO vo = RoleConvert.INSTANCE.RoleDOtoVO(item);
            records.add(vo);
        }
        result.setRecords(records);
        return result;
    }

    @Override
    public RoleGetOneVO getOneRole(Long id) {
        LambdaQueryWrapper<RoleDO> roleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleDOLambdaQueryWrapper.eq(RoleDO::getId,id);
        RoleDO roleDO =  roleMapper.selectOne(roleDOLambdaQueryWrapper);
        RoleGetOneVO vo =  RoleConvert.INSTANCE.doToGetOneVO(roleDO);
        return vo;
    }

    @Override
    public List<RoleListVO> listEnableRole() {
        LambdaQueryWrapper<RoleDO> roleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleDOLambdaQueryWrapper.eq(RoleDO::getStatus, RoleStatusEnum.ROLE_ENABLE.status);
        List<RoleDO> roleDOS = roleMapper.selectList(roleDOLambdaQueryWrapper);
        List<RoleListVO> result = RoleConvert.INSTANCE.doToListRoleVO(roleDOS);
        return result;
    }
}
