package com.example.itprom.dto.employee;

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

    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("profession_id")
    private Long professionId;
}

