package api.permission;

/**
 * @author ltx
 */
public interface PermissionApi {

    boolean hasAnyPermission(Long authenticatedUserId, String... permissions);

    boolean hasAnyRole(Long authenticatedUserId, String... roles);
}
