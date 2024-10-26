package com.example.itprom.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.itprom.dto.department.DepartmentCreateDTO;
import com.example.itprom.dto.employee.EmployeeCreateDTO;
import com.example.itprom.dto.profession.ProfessionCreateDTO;
import com.example.itprom.service.DepartmentService;
import com.example.itprom.service.EmployeeService;
import com.example.itprom.service.ProfessionService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var profession1 = new ProfessionCreateDTO();
        profession1.setName("Java developer");
        profession1.setDescription(
            "A Java Developer is a programmer who designs, develops,"
            + "and manages Java-based applications and software."
        );
        professionService.create(profession1);

        var profession2 = new ProfessionCreateDTO();
        profession2.setName("QA tester");
        profession2.setDescription(
            "QA testers assess software and development processes to "
            + "meet client requirements and organisational standards."
        );
        professionService.create(profession2);

        var department1 = new DepartmentCreateDTO();
        department1.setName("Application development");
        department1.setDescription("Parent department description");
        departmentService.create(department1);

        var department2 = new DepartmentCreateDTO();
        department2.setName("Testing department");
        department2.setDescription("Child department description");
        department2.setParentDepartmentId(1L);
        departmentService.create(department2);

        var employee1 = new EmployeeCreateDTO();
        employee1.setFullName("Sidorov Sergey Ivanovich");
        employee1.setDescription("Employee1 description");
        employee1.setDepartmentId(1L);
        employee1.setProfessionId(1L);
        employeeService.create(employee1);

        var employee2 = new EmployeeCreateDTO();
        employee2.setFullName("Jane Doe");
        employee2.setDescription("Employee2 description");
        employee2.setDepartmentId(2L);
        employee2.setProfessionId(2L);
        employeeService.create(employee2);
    }
}
