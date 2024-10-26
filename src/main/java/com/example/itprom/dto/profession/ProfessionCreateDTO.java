package com.example.itprom.dto.profession;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionCreateDTO {

    @NotBlank
    @Column(unique = true)
    private String name;

    private String description;
}

