package com.example.itprom.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "departments")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
public class Department implements BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @NotBlank
    @Column(unique = true)
    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;

    @ToString.Include
    private String description;

    @OneToMany(mappedBy = "parentDepartment",
        cascade = CascadeType.MERGE,
        orphanRemoval = true)
    private Set<Department> departments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    @ToString.Include
    private Department parentDepartment;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();
}
