package com.ocean.api.permission;

import api.permission.DataPermissionApi;
import api.permission.dto.DeptDataPermissionDTO;
import org.springframework.stereotype.Service;

@Service
public class DataPermissionApiImpl implements DataPermissionApi {
    @Override
    public DeptDataPermissionDTO getUserRelationDept(Long authenticatedUserId) {
        return null;
    }
}
