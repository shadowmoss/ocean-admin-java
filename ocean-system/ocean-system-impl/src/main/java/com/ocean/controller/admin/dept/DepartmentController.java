package com.ocean.controller.admin.dept;

import com.ocean.common.restful.RestfulResponse;
import com.ocean.controller.admin.dept.vo.DepartmentAddVO;
import com.ocean.controller.admin.dept.vo.DepartmentUpdateVO;
import com.ocean.controller.admin.dept.vo.DepartmentVO;
import com.ocean.controller.admin.dept.vo.GetDepartmentVO;
import com.ocean.service.dept.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;
    @GetMapping("/get")
    @Operation(summary = "查询部门信息")
    public RestfulResponse<List<DepartmentVO>> getDepartment(GetDepartmentVO getDepartmentVO){
        List<DepartmentVO> departmentVOS =  departmentService.listDepart(getDepartmentVO);
        return RestfulResponse.ok("查询成功",departmentVOS);
    }
    @PostMapping("/add")
    @Operation(summary = "插入部门信息")
    public RestfulResponse<String> addNewDepartment(@RequestBody DepartmentAddVO departmentAddVO){
        departmentService.addNewDepartment(departmentAddVO);
        return RestfulResponse.ok("新增成功","新增成功");
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除记录")
    public RestfulResponse<String> deleteDepartment(List<Long> ids){
        departmentService.deleteDepartment(ids);
        return RestfulResponse.ok("删除成功","删除成功");
    }

    @PutMapping("/update")
    @Operation(summary = "更新一条记录")
    public RestfulResponse<String> updateDepartment(@RequestBody DepartmentUpdateVO departmentUpdate){
        departmentService.updateDepartment(departmentUpdate);
        return RestfulResponse.ok("更新成功","更新成功");
    }
}
