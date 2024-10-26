package com.example.itprom.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeCreateDTO {

    @Size(min = 1)
    @Column(unique = true)
    @JsonProperty("full_name")
    private String fullName;

    private String description;

    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("profession_id")
    private Long professionId;
}

