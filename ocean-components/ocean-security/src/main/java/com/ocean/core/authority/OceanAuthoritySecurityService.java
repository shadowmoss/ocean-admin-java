package com.ocean.core.authority;

/**
 * 授权服务接口
 * 根据当前访问用户的查询，是否拥有相应的权限。
 * @author ltx
 */
public interface OceanAuthoritySecurityService {
    /**
     *
     * @param permission
     * @return
     */
    boolean hasPermission(String permission);

    /**
     *
     * @param permissions
     * @return
     */
    boolean hasAnyPermission(String... permissions);

    /**
     *
     * @param role
     * @return
     */
    boolean hasRole(String role);

    /**
     *
     * @param role
     * @return
     */
    boolean hasAnyRole(String... roles);
}
