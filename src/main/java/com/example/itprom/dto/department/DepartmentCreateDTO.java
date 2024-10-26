package com.example.itprom.dto.department;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentCreateDTO {

    @NotBlank
    @Column(unique = true)
    private String name;

    private String description;

    @JsonProperty("parent_department_id")
    private Long parentDepartmentId;
}

