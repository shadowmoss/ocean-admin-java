package com.ocean.service.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ocean.enums.MenuType;
import com.ocean.enums.RoleCodeEnum;
import com.ocean.mapper.permission.MenuMapper;
import com.ocean.mapper.permission.SystemRoleMenuMapper;
import com.ocean.mapper.permission.SystemUserRoleMapper;
import com.ocean.model.entity.MenuDO;
import com.ocean.model.entity.SystemRoleMenuDO;
import com.ocean.model.entity.SystemUserRoleDO;
import com.ocean.model.vo.permission.RoleBindMenuVO;
import com.ocean.model.vo.permission.RoleBindedMenuVO;
import com.ocean.model.vo.permission.UserBindRoleVO;
import com.ocean.model.vo.permission.UserBindedRoleVO;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ltx
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Resource
    private SystemUserRoleMapper systemUserRoleMapper;
    @Resource
    private SystemRoleMenuMapper systemRoleMenuMapper;

    @Resource
    private MenuMapper menuMapper;
    @Override
    public Boolean userBindRole(UserBindRoleVO userBindRoleVO) {
        List<Long> roleIds = userBindRoleVO.getRoleIds();
        // 查询该用户已绑定的角色
        LambdaQueryWrapper<SystemUserRoleDO> systemUserRoleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemUserRoleDOLambdaQueryWrapper.eq(SystemUserRoleDO::getUserId,userBindRoleVO.getUserId());
        // 已绑定的角色信息
        List<SystemUserRoleDO> systemUserRoleDOS = systemUserRoleMapper.selectList(systemUserRoleDOLambdaQueryWrapper);
        // 已绑定的角色id
        List<Long> bindRoleList = systemUserRoleDOS.stream().map(SystemUserRoleDO::getRoleId).collect(Collectors.toList());
        // 待删除的数据集合
        List<SystemUserRoleDO> waitToDelete = systemUserRoleDOS.stream().filter(item -> !roleIds.contains(item.getRoleId())).collect(Collectors.toList());
        // 待新增的绑定数据
        List<Long> insertRoleList = roleIds.stream().filter(item -> !bindRoleList.contains(item)).collect(Collectors.toList());
        for(Long roleId :insertRoleList){
            // 插入新角色绑定时,先查询之前是否删除过该绑定,如果有,将原先的数据更新。
            SystemUserRoleDO oldData = systemUserRoleMapper.selectExist(roleId,userBindRoleVO.getUserId());
            if(oldData!=null){
                oldData.setDeleted(0);
                systemUserRoleMapper.updateDeleted(oldData);
            }else{
                SystemUserRoleDO systemUserRoleDO = new SystemUserRoleDO();
                systemUserRoleDO.setUserId(userBindRoleVO.getUserId());
                systemUserRoleDO.setRoleId(roleId);
                systemUserRoleMapper.insert(systemUserRoleDO);
            }
        }
        if(!waitToDelete.isEmpty()){
            systemUserRoleMapper.deleteBatchIds(waitToDelete.stream().map(SystemUserRoleDO::getId).collect(Collectors.toList()));
        }
        return true;
    }

    @Override
    public Boolean roleBindMenu(RoleBindMenuVO roleBindMenuVO) {
        List<Long> menuIds = roleBindMenuVO.getMenuIds();
        // 查询该角色已绑定的菜单
        LambdaQueryWrapper<SystemRoleMenuDO> systemRoleMenuDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemRoleMenuDOLambdaQueryWrapper.eq(SystemRoleMenuDO::getRoleId,roleBindMenuVO.getRoleId());
        // 当前角色已绑定的菜单
        List<SystemRoleMenuDO> systemRoleMenuDOS = systemRoleMenuMapper.selectList(systemRoleMenuDOLambdaQueryWrapper);
        // 已绑定的菜单id
        List<Long> bindMenuIds = systemRoleMenuDOS.stream().map(SystemRoleMenuDO::getMenuId).collect(Collectors.toList());
        // 待删除的绑定数据
        List<SystemRoleMenuDO> waitToDelete = systemRoleMenuDOS.stream().filter(item -> !menuIds.contains(item.getMenuId())).collect(Collectors.toList());
        // 待新增的绑定数据
        List<Long> insertMenuList = menuIds.stream().filter(item -> !bindMenuIds.contains(item)).collect(Collectors.toList());
        for(Long menuId :insertMenuList){
            SystemRoleMenuDO oldData = systemRoleMenuMapper.selectExist(menuId,roleBindMenuVO.getRoleId());
            if(oldData!= null){
                oldData.setDeleted(0);
                systemRoleMenuMapper.updateDeleted(oldData);
            }else{
                SystemRoleMenuDO systemRoleMenuDO = new SystemRoleMenuDO();
                systemRoleMenuDO.setRoleId(roleBindMenuVO.getRoleId());
                systemRoleMenuDO.setMenuId(menuId);
                systemRoleMenuMapper.insert(systemRoleMenuDO);
            }

        }
        if(!waitToDelete.isEmpty()) {
            systemRoleMenuMapper.deleteBatchIds(waitToDelete.stream().map(SystemRoleMenuDO::getId).collect(Collectors.toList()));
        }
        return true;
    }

    @Override
    public List<UserBindedRoleVO> listUserBindedRole(Long id) {
        List<UserBindedRoleVO> userBindedRoleVOS = systemUserRoleMapper.listUserBindedRoles(id);
        return userBindedRoleVOS;
    }

    @Override
    public List<RoleBindedMenuVO> listRoleBindedMenu(Long id) {
        List<RoleBindedMenuVO> roleBindedMenuVOS = systemRoleMenuMapper.listRoleBindedMenus(id);
        return roleBindedMenuVOS;
    }

    @Override
    public List<MenuDO> getAllPermissions(List<Long> roleIds) {
        return systemRoleMenuMapper.getAllPermissions(roleIds);
    }

    @Override
    public List<MenuDO> getAuthorityMenusByRoles(List<Long> roleIds) {
        List<Integer> menuTypes = new ArrayList<>();
        menuTypes.add(MenuType.MENU_PAGE.type);
        menuTypes.add(MenuType.MENU_DIRECTORY.type);
        return systemRoleMenuMapper.getAllMenusByRoles(menuTypes,roleIds);
    }

    @Override
    public boolean hasAnyPermission(Long authenticatedUserId, String... permissions) {
        // 没有权限要求
        if(Arrays.isNullOrEmpty(permissions)){
            return true;
        }
        // 查询当前用户所拥有的角色
        List<UserBindedRoleVO> userBindedRoleVOS = systemUserRoleMapper.listUserBindedRoles(authenticatedUserId);
        // 判断当前用户的角色是否为超级管理员
        for(UserBindedRoleVO item :userBindedRoleVOS){
            // 如果该角色包含有超级管理员角色,默认拥有全部权限,直接返回
            if(RoleCodeEnum.SUPER_ADMIN.code.equals(item.getCode())){
                return true;
            }
        }
        // 没有超级管理员角色的用户权限判断
            List<Long> roleIds = userBindedRoleVOS.stream().map(UserBindedRoleVO::getId).collect(Collectors.toList());
            // 1.查询当前用户所拥有的菜单权限
            List<MenuDO> allMenusByRoles = systemRoleMenuMapper.getAllMenusByRoles(null,roleIds);
            List<String> hadPermission = allMenusByRoles.stream().map(MenuDO::getPermission).collect(Collectors.toList());
            // 2.如果所拥有的权限Permission中,刚好包含所需要的permission则返回为true。
            for(String permission :permissions){
                if(hadPermission.contains(permission)){
                    return true;
                }
            }
        return false;
    }

    @Override
    public boolean hasAnyRole(Long authenticatedUserId, String... roles) {
        // 当前没有权限要求
        if(Arrays.isNullOrEmpty(roles)){
            return true;
        }
        // 查询当前用户所拥有的角色信息
        List<UserBindedRoleVO> userBindedRoleVOS = systemUserRoleMapper.listUserBindedRoles(authenticatedUserId);
        // 如果当前用户角色含有超级管理员,默认拥有所有权限
        for(UserBindedRoleVO item :userBindedRoleVOS){
            if(item.getCode().equals(RoleCodeEnum.SUPER_ADMIN.code)){
                return true;
            }
        }
        // 如果当前用户角色没有超级管理员
        List<String> roleLists = userBindedRoleVOS.stream().map(UserBindedRoleVO::getCode).collect(Collectors.toList());
        // 只要当前用户含有该角色,
        for(String item:roles){
            if(roleLists.contains(item)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MenuDO> getAllMenus() {
        List<Integer> menuTypes = new ArrayList<>();
        menuTypes.add(MenuType.MENU_PAGE.type);
        menuTypes.add(MenuType.MENU_DIRECTORY.type);
        LambdaQueryWrapper<MenuDO> menuDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuDOLambdaQueryWrapper.in(MenuDO::getType,menuTypes);
        return menuMapper.selectList(menuDOLambdaQueryWrapper);
    }
}
