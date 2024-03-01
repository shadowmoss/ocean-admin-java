package api.permission.dto;

import lombok.Data;

import java.util.Set;

@Data
public class DeptDataPermissionDTO {
    /**
     * 查看所有部门
     */
    private Boolean all;
    /**
     * 自身所在部门
     */
    private Boolean selfDept;
    /**
     * 自身所在部门及其子部门
     */
    private Boolean deptAndChild;
    /**
     * 涉及到的部门id
     */
    private Set<Long> deptIds;
}
