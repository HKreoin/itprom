package com.example.itprom.dto.department;

import org.openapitools.jackson.nullable.JsonNullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateDTO {

    @NotBlank
    @Column(unique = true)
    private JsonNullable<String> name;

    private JsonNullable<String> description;

    @JsonProperty("parent_department_id")
    private JsonNullable<Long> parentDepartmentId;
}

