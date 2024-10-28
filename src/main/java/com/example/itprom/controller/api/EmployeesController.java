package com.example.itprom.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.itprom.dto.employee.EmployeeCreateDTO;
import com.example.itprom.dto.employee.EmployeeDTO;
import com.example.itprom.dto.employee.EmployeeUpdateDTO;
import com.example.itprom.service.EmployeeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
@Tag(name = "Employees", description = "API для работы с сотрудниками")
public class EmployeesController {

    @Autowired
    private EmployeeService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить список сотрудников",
        responses = {@ApiResponse(description = "Успешное получение списка сотрудников")})
    ResponseEntity<List<EmployeeDTO>> index() {
        var dtos = service.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(dtos.size()))
                .body(dtos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить карточку сотрудника по ID",
        responses = {@ApiResponse(description = "Успешное получение карточки сотрудника")})
    EmployeeDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать карточку сотрудника",
        responses = {@ApiResponse(description = "Успешное создание карточки сотрудника")})
    EmployeeDTO create(@Valid @RequestBody EmployeeCreateDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить карточку сотрудника",
        responses = {@ApiResponse(description = "Успешное обновление карточки сотрудника")})
    EmployeeDTO update(@PathVariable Long id, @Valid @RequestBody EmployeeUpdateDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить карточку сотрудника",
        responses = {@ApiResponse(description = "Успешное удаление карточки сотрудника")})
    void destroy(@PathVariable Long id) {
        service.delete(id);
    }

}
