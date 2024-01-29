package com.ocean.service.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ocean.convert.MenuConvert;
import com.ocean.mapper.permission.MenuMapper;
import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ltx
 */
@Service
public class MenuServiceImpl implements MenuService{
    @Resource
    private MenuMapper menuMapper;
    @Override
    public Long createMenu(MenuCreateVO menuCreateVO) {
        MenuDO menuDO = MenuConvert.INSTANCE.createVOtoDO(menuCreateVO);
        int insert = menuMapper.insert(menuDO);
        return menuDO.getId();
    }

    @Override
    public Boolean updateMenu(MenuUpdateVO menuUpdateVO) {
        MenuDO menuDO = MenuConvert.INSTANCE.updateVOtoDO(menuUpdateVO);
        int flag = menuMapper.updateById(menuDO);
        if(flag>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean deleteMenu(Long id){
        LambdaQueryWrapper<MenuDO> menuDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuDOLambdaQueryWrapper.eq(MenuDO::getParentId,id);
        int childCount =  menuMapper.selectCount(menuDOLambdaQueryWrapper);
        if(childCount > 0){
            throw new RuntimeException("当前目录存在子目录无法删除");
        }
        menuMapper.deleteById(id);
        return true;
    }

    @Override
    public List<MenuResVO> listMenu(MenuListVO menuListVO) {
        LambdaQueryWrapper<MenuDO> menuDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!Objects.isNull(menuListVO.getName())){
            menuDOLambdaQueryWrapper.like(MenuDO::getName,menuListVO.getName());
        }
        if(!Objects.isNull(menuListVO.getStatus())){
            menuDOLambdaQueryWrapper.eq(MenuDO::getStatus,menuListVO.getStatus());
        }
        List<MenuDO> menuDOS = menuMapper.selectList(menuDOLambdaQueryWrapper);
        List<MenuResVO> result = new ArrayList<>();
        for(MenuDO item:menuDOS) {
            MenuResVO vo = MenuConvert.INSTANCE.DOtoListVO(item);
            result.add(vo);
        }
        Map<Long,MenuResVO> treeNode = new HashMap<>();
        for(MenuResVO vo : result){
            if(!treeNode.containsKey(vo.getId())){
                treeNode.put(vo.getId(),vo);
            }
            MenuResVO parent = null;
            if(treeNode.containsKey(vo.getParentId())){
                parent = treeNode.get(vo.getParentId());
            }
            if(parent==null){
                continue;
            }
            if(parent.getChildren()==null){
                parent.setChildren(new ArrayList<>());
            }
            List<MenuResVO> childrens = parent.getChildren();
            childrens.add(vo);
        }
        List<MenuResVO> menuResVOS = new ArrayList<>();
        for(Map.Entry<Long,MenuResVO> item : treeNode.entrySet()){
            if(item.getValue().getParentId()==0){
                menuResVOS.add(item.getValue());
            }
        }
        return menuResVOS;
    }

    @Override
    public MenuGetOneVO getOne(Long id) {
        LambdaQueryWrapper<MenuDO> menuDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuDOLambdaQueryWrapper.eq(MenuDO::getId,id);
        MenuDO menuDO = menuMapper.selectOne(menuDOLambdaQueryWrapper);
        MenuGetOneVO vo =  MenuConvert.INSTANCE.DOtoGetOneVO(menuDO);
        return vo;
    }
}
