package com.example.itprom.dto.profession;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionCreateDTO {

    @Size(min = 1)
    @Column(unique = true)
    private String name;

    private String description;
}

