package com.example.itprom.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
public class Employee implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Size(min = 1)
    @ToString.Include
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    @ToString.Include
    private Profession profession;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Include
    private Department department;

    @Size(min = 1)
    @ToString.Include
    private String description;

}
