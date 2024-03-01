package com.ocean.convert;

import com.ocean.controller.admin.dept.vo.DepartmentAddVO;
import com.ocean.controller.admin.dept.vo.DepartmentUpdateVO;
import com.ocean.controller.admin.dept.vo.DepartmentVO;
import com.ocean.model.entity.DepartmentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentConvert {
    public DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

    DepartmentDO addVOtoDO(DepartmentAddVO departmentAddVO);

    DepartmentDO updateToDo(DepartmentUpdateVO departmentUpdate);

    DepartmentVO dotoVo(DepartmentDO item);
}
