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
        var profession = new ProfessionCreateDTO();
        profession.setName("Директор");
        profession.setDescription(
            "Руководит компанией, занимается управлением "
            + "процессов создания и поддержки программных продуктов"
        );
        professionService.create(profession);

        var profession1 = new ProfessionCreateDTO();
        profession1.setName("Маркетолог");
        profession1.setDescription(
            "Создает стратегию продвижения продуктов и услуг, чтобы увеличить продажи и общий доход компании"
        );
        professionService.create(profession1);

        var profession2 = new ProfessionCreateDTO();
        profession2.setName("Java разработчик");
        profession2.setDescription(
            "Java разработчик выполняет разработку программного "
            + "обеспечения на языке программирования Java."
        );
        professionService.create(profession2);

        var profession3 = new ProfessionCreateDTO();
        profession3.setName("QA тестировщик");
        profession3.setDescription(
            "QA тестировщик выполняет тестирование программного обеспечения."
        );
        professionService.create(profession3);

        var department = new DepartmentCreateDTO();
        department.setName("Отдел управления");
        department.setDescription("Отдел управления программного обеспечения.");
        departmentService.create(department);

        var department1 = new DepartmentCreateDTO();
        department1.setName("Отдел маркетинга");
        department1.setDescription("Отдел маркетинга программного обеспечения.");
        department1.setParentDepartmentId(1L);
        departmentService.create(department1);

        var department2 = new DepartmentCreateDTO();
        department2.setName("Отдел разработки");
        department2.setDescription("Основной отдел разработки программного обеспечения.");
        department2.setParentDepartmentId(1L);
        departmentService.create(department2);

        var department3 = new DepartmentCreateDTO();
        department3.setName("Отдел тестирования");
        department3.setDescription("Отдел тестирования программного обеспечения.");
        department3.setParentDepartmentId(2L);
        departmentService.create(department3);

        var employee = new EmployeeCreateDTO();
        employee.setFullName("Вишневский Андрей Анатольевич");
        employee.setDescription("Любит внедрять информационные системы");
        employee.setDepartmentId(1L);
        employee.setProfessionId(1L);
        employeeService.create(employee);

        var employee1 = new EmployeeCreateDTO();
        employee1.setFullName("Иванова Наталья Николаевна");
        employee1.setDescription("Продвигает продукт на рынок");
        employee1.setDepartmentId(2L);
        employee1.setProfessionId(2L);
        employeeService.create(employee1);

        var employee2 = new EmployeeCreateDTO();
        employee2.setFullName("Хабиров Ильшат Ришатович");
        employee2.setDescription("Любит программировать");
        employee2.setDepartmentId(3L);
        employee2.setProfessionId(3L);
        employeeService.create(employee2);

        var employee3 = new EmployeeCreateDTO();
        employee3.setFullName("Сидоров Семен Семенович");
        employee3.setDescription("Любит тестировать");
        employee3.setDepartmentId(4L);
        employee3.setProfessionId(4L);
        employeeService.create(employee3);
    }
}
