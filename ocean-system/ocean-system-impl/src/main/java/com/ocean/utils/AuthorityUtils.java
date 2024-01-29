package com.ocean.utils;

import com.ocean.convert.AuthorityConvert;
import com.ocean.enums.MenuConstants;
import com.ocean.model.entity.MenuDO;
import com.ocean.model.vo.permission.AuthorityMenuVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ltx
 */
public class AuthorityUtils {
    public static List<AuthorityMenuVO> generateTree(List<MenuDO> menuDOS){
        // 根据Sort字段进行数据排序
        menuDOS.sort(Comparator.comparing(MenuDO::getSort));
        Map<Long,AuthorityMenuVO> treeNodeMap = new LinkedHashMap<>();
        // 将所有节点存入map当中
        menuDOS.forEach(item-> treeNodeMap.put(item.getId(), AuthorityConvert.INSTANCE.MenuDOtoAuthorityVO(item)));

        // 开始构建树
        // 非父节点的item
        treeNodeMap.values().stream().filter(item-> !item.getParentId().equals(MenuConstants.ROOT_ID)).forEach(item->
        {
            AuthorityMenuVO parentNode = treeNodeMap.get(item.getParentId());
            if(parentNode==null){
                return;
            }
            List<AuthorityMenuVO> children = parentNode.getChildren();
            if(children==null){
                children = new ArrayList<>();
                parentNode.setChildren(children);
            }
            children.add(item);
        });
        return treeNodeMap.values().stream().filter(item->item.getParentId().equals(MenuConstants.ROOT_ID)).collect(Collectors.toList());
    }
}
