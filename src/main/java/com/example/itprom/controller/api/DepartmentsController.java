package com.example.itprom.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.itprom.dto.department.DepartmentCreateDTO;
import com.example.itprom.dto.department.DepartmentDTO;
import com.example.itprom.dto.department.DepartmentUpdateDTO;
import com.example.itprom.service.DepartmentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<DepartmentDTO>> index() {
        var dtos = service.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(dtos.size()))
                .body(dtos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    DepartmentDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    DepartmentDTO create(@Valid @RequestBody DepartmentCreateDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    DepartmentDTO update(@PathVariable Long id, @Valid @RequestBody DepartmentUpdateDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        service.delete(id);
    }

}
