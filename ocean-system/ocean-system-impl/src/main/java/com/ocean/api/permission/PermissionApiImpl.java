package com.ocean.api.permission;

import api.permission.PermissionApi;
import com.ocean.service.permission.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ltx
 */
@Service
public class PermissionApiImpl implements PermissionApi {
    @Resource
    private PermissionService permissionService;
    @Override
    public boolean hasAnyPermission(Long authenticatedUserId, String... permissions) {
        return permissionService.hasAnyPermission(authenticatedUserId,permissions);
    }

    @Override
    public boolean hasAnyRole(Long authenticatedUserId, String... roles) {
        return permissionService.hasAnyRole(authenticatedUserId,roles);
    }
}
