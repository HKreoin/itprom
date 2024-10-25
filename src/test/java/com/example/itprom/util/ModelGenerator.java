package com.example.itprom.util;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.itprom.model.Department;
import com.example.itprom.model.Employee;
import com.example.itprom.model.Profession;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import net.datafaker.Faker;

@Getter
@Component
public class ModelGenerator {

    private Model<Employee> employeeModel;

    private Model<Department> departmentModel;

    private Model<Profession> professionModel;

    @Autowired
    private Faker faker;

    @PostConstruct
    public void init() {
        employeeModel = Instancio.of(Employee.class)
                .ignore(Select.field(Employee::getId))
                .supply(Select.field(Employee::getFullName), () -> faker.name().fullName())
                .supply(Select.field(Employee::getDescription), () -> faker.lorem().sentence())
                .ignore(Select.field(Employee::getProfession))
                .ignore(Select.field(Employee::getDepartment))
                .toModel();

        departmentModel = Instancio.of(Department.class)
                .ignore(Select.field(Department::getId))
                .supply(Select.field(Department::getName), () -> faker.lorem().sentence())
                .supply(Select.field(Department::getDescription), () -> faker.lorem().sentence())
                .ignore(Select.field(Department::getParentDepartment))
                .ignore(Select.field(Department::getDepartments))
                .ignore(Select.field(Department::getEmployees))
                .toModel();

        professionModel = Instancio.of(Profession.class)
                .ignore(Select.field(Profession::getId))
                .supply(Select.field(Profession::getName), () -> faker.company().profession())
                .supply(Select.field(Profession::getDescription), () -> faker.lorem().sentence())
                .ignore(Select.field(Profession::getEmployees))
                .toModel();
    }

}
