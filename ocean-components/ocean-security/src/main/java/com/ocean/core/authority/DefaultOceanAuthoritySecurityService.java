package com.ocean.core.authority;

import api.permission.PermissionApi;
import com.ocean.core.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ltx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultOceanAuthoritySecurityService implements OceanAuthoritySecurityService{
    private PermissionApi permissionApi;
    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    @Override
    public boolean hasAnyPermission(String... permissions) {
        return permissionApi.hasAnyPermission(SecurityUtil.getAuthenticatedUserId(),permissions);
    }

    @Override
    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        return permissionApi.hasAnyRole(SecurityUtil.getAuthenticatedUserId(),roles);
    }
}
