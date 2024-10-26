package com.example.itprom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import com.example.itprom.dto.profession.ProfessionCreateDTO;
import com.example.itprom.dto.profession.ProfessionDTO;
import com.example.itprom.dto.profession.ProfessionUpdateDTO;
import com.example.itprom.exception.ResourceNotFoundException;
import com.example.itprom.mapper.ProfessionMapper;
import com.example.itprom.repository.ProfessionRepository;

@Service
public class ProfessionService {
    @Autowired
    private ProfessionRepository repository;

    @Autowired
    private ProfessionMapper mapper;

    public List<ProfessionDTO> findAll() {
        var models = repository.findAll();
        var result = models.stream()
                .map(mapper :: map)
                .toList();
        return result;
    }

    public ProfessionDTO findById(Long id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Profession with id: " + id + "not found"));
        return mapper.map(model);
    }

    public ProfessionDTO create(ProfessionCreateDTO data) {
        var model = mapper.map(data);
        repository.save(model);
        return mapper.map(model);
    }

    public ProfessionDTO update(Long id, ProfessionUpdateDTO data) {
        var model = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Profession with id: " + id + "not found"));
        mapper.update(data, model);
        repository.save(model);
        return mapper.map(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
