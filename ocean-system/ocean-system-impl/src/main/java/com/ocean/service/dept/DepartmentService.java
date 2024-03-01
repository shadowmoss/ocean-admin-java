package com.ocean.service.dept;

import com.ocean.controller.admin.dept.vo.DepartmentAddVO;
import com.ocean.controller.admin.dept.vo.DepartmentUpdateVO;
import com.ocean.controller.admin.dept.vo.DepartmentVO;
import com.ocean.controller.admin.dept.vo.GetDepartmentVO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentVO> listDepart(GetDepartmentVO getDepartmentVO);

    void addNewDepartment(DepartmentAddVO departmentAddVO);

    void deleteDepartment(List<Long> ids);

    void updateDepartment(DepartmentUpdateVO departmentUpdate);
}
