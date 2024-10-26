package com.example.itprom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import com.example.itprom.dto.employee.EmployeeCreateDTO;
import com.example.itprom.dto.employee.EmployeeDTO;
import com.example.itprom.dto.employee.EmployeeUpdateDTO;
import com.example.itprom.exception.ResourceNotFoundException;
import com.example.itprom.mapper.EmployeeMapper;
import com.example.itprom.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeMapper mapper;

    public List<EmployeeDTO> findAll() {
        var models = repository.findAll();
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public EmployeeDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Employee with id: " + id + "not found"));
        return mapper.map(model);
    }

    public EmployeeDTO create(EmployeeCreateDTO data) {
        var model = mapper.map(data);
        repository.save(model);
        return mapper.map(model);
    }

    public EmployeeDTO update(Long id, EmployeeUpdateDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Employee with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
