package com.ocean.service.dept.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ocean.controller.admin.dept.vo.DepartmentAddVO;
import com.ocean.controller.admin.dept.vo.DepartmentUpdateVO;
import com.ocean.controller.admin.dept.vo.DepartmentVO;
import com.ocean.controller.admin.dept.vo.GetDepartmentVO;
import com.ocean.convert.DepartmentConvert;
import com.ocean.mapper.dept.DepartmentMapper;
import com.ocean.model.entity.DepartmentDO;
import com.ocean.service.dept.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Resource
    DepartmentMapper departmentMapper;
    @Override
    public List<DepartmentVO> listDepart(GetDepartmentVO getDepartmentVO) {
        List<DepartmentVO> result = new ArrayList<>();
        List<DepartmentVO> departmentDOS = departmentMapper.listDepartment(getDepartmentVO.getName());
        return departmentDOS;
    }

    @Override
    public void addNewDepartment(DepartmentAddVO departmentAddVO) {
        DepartmentDO entity = DepartmentConvert.INSTANCE.addVOtoDO(departmentAddVO);
        departmentMapper.insert(entity);
    }

    @Override
    public void deleteDepartment(List<Long> ids) {
        LambdaQueryWrapper<DepartmentDO> departmentDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        departmentDOLambdaQueryWrapper.in(DepartmentDO::getId,ids);
        departmentMapper.delete(departmentDOLambdaQueryWrapper);
    }

    @Override
    public void updateDepartment(DepartmentUpdateVO departmentUpdate) {
        DepartmentDO entity =  DepartmentConvert.INSTANCE.updateToDo(departmentUpdate);
        int count = departmentMapper.update(entity,null);
    }


}
