package com.ocean.service.permission;

import com.ocean.model.vo.permission.*;

import java.util.List;

/**
 * @author ltx
 */
public interface MenuService {
    Long createMenu(MenuCreateVO menuCreateVO);

    Boolean updateMenu(MenuUpdateVO menuUpdateVO);

    Boolean deleteMenu(Long id);

    List<MenuResVO> listMenu(MenuListVO menuListVO);

    MenuGetOneVO getOne(Long id);
}
