package com.example.itprom.dto.employee;

import org.openapitools.jackson.nullable.JsonNullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdateDTO {

    @NotBlank
    @Column(unique = true)
    @JsonProperty("full_name")
    private JsonNullable<String> fullName;

    private JsonNullable<String> description;

    @JsonProperty("department_id")
    private JsonNullable<Long> departmentId;

    @JsonProperty("profession_id")
    private JsonNullable<Long> professionId;
}

