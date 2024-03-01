package api.permission;

import api.permission.dto.DeptDataPermissionDTO;

public interface DataPermissionApi {
    DeptDataPermissionDTO getUserRelationDept(Long authenticatedUserId);
}
