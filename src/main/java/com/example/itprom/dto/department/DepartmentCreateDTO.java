package com.example.itprom.dto.department;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateDTO {

    @Size(min = 1)
    @Column(unique = true)
    private String name;

    private String description;

    @JsonProperty("parent_department_id")
    private Long parentDepartmentId;
}

