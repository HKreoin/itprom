package com.example.itprom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.itprom.dto.employee.EmployeeCreateDTO;
import com.example.itprom.dto.employee.EmployeeDTO;
import com.example.itprom.dto.employee.EmployeeUpdateDTO;
import com.example.itprom.model.Employee;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = {JsonNullableMapper.class, ReferenceMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class EmployeeMapper {

    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "profession", source = "professionId")
    public abstract Employee map(EmployeeCreateDTO data);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "professionId", source = "profession.id")
    public abstract EmployeeDTO map(Employee model);
    
    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "profession", source = "professionId")
    public abstract Employee map(EmployeeDTO data);

    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "profession", source = "professionId")
    public abstract void update(EmployeeUpdateDTO update, @MappingTarget Employee target);

}
