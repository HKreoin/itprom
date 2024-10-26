package com.example.itprom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import com.example.itprom.dto.department.DepartmentCreateDTO;
import com.example.itprom.dto.department.DepartmentDTO;
import com.example.itprom.dto.department.DepartmentUpdateDTO;
import com.example.itprom.exception.ResourceNotFoundException;
import com.example.itprom.mapper.DepartmentMapper;
import com.example.itprom.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private DepartmentMapper mapper;

    public List<DepartmentDTO> findAll() {
        var models = repository.findAll();
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public DepartmentDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Department with id: " + id + "not found"));
        return mapper.map(model);
    }

    public DepartmentDTO create(DepartmentCreateDTO data) {
        var model = mapper.map(data);
        repository.save(model);
        return mapper.map(model);
    }

    public DepartmentDTO update(Long id, DepartmentUpdateDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Department with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
