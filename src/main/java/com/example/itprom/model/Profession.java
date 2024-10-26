package com.example.itprom.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "professions")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
public class Profession implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Size(min = 1)
    @Column(unique = true)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;

    @ToString.Include
    private String description;

    @OneToMany(mappedBy = "profession", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();
}
