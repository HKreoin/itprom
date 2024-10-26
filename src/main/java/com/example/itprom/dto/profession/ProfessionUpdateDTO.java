package com.example.itprom.dto.profession;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionUpdateDTO {

    @NotBlank
    @Column(unique = true)
    private JsonNullable<String> name;

    private JsonNullable<String> description;
}

