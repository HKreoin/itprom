package com.example.itprom.dto.department;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;

    private String name;

    private String description;

    @JsonProperty("parent_department")
    private DepartmentDTO parentDepartment;
}

