package com.example.itprom.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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

    @Size(min = 1)
    @ToString.Include
    private String name;

    @ToString.Include
    private String description;

    @OneToMany(mappedBy = "parentDepartment", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Department> departments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    @ToString.Include
    private Department parentDepartment;

    @OneToMany(mappedBy = "department", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
}
