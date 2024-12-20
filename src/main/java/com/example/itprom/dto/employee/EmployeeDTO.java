package com.example.itprom.dto.employee;

import com.example.itprom.dto.department.DepartmentDTO;
import com.example.itprom.dto.profession.ProfessionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;

    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("department")
    private DepartmentDTO department;

    @JsonProperty("profession")
    private ProfessionDTO profession;
}

