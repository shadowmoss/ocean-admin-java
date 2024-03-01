package com.ocean.mapper.dept;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.controller.admin.dept.vo.DepartmentVO;
import com.ocean.model.entity.DepartmentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {
    List<DepartmentVO> listDepartment(String name);
}
