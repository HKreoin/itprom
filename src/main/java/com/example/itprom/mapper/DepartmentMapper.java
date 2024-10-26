package com.example.itprom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.itprom.dto.department.DepartmentCreateDTO;
import com.example.itprom.dto.department.DepartmentDTO;
import com.example.itprom.dto.department.DepartmentUpdateDTO;
import com.example.itprom.model.Department;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class DepartmentMapper {

    @Mapping(target = "parentDepartment", source = "parentDepartmentId")
    public abstract Department map(DepartmentCreateDTO data);

    @Mapping(target = "parentDepartmentId", source = "parentDepartment.id")
    public abstract DepartmentDTO map(Department model);

    @Mapping(target = "parentDepartment", source = "parentDepartmentId")
    public abstract Department map(DepartmentDTO data);

    @Mapping(target = "parentDepartment", source = "parentDepartmentId")
    public abstract void update(DepartmentUpdateDTO update, @MappingTarget Department target);

}
