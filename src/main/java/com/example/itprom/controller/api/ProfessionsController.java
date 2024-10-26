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

import com.example.itprom.dto.profession.ProfessionCreateDTO;
import com.example.itprom.dto.profession.ProfessionDTO;
import com.example.itprom.dto.profession.ProfessionUpdateDTO;
import com.example.itprom.service.ProfessionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/professions")
@Tag(name = "Professions", description = "API для работы с профессиями")
public class ProfessionsController {

    @Autowired
    private ProfessionService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить список профессий",
        responses = {@ApiResponse(description = "Успешное получение списка профессий")})
    ResponseEntity<List<ProfessionDTO>> index() {
        var dtos = service.findAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(dtos.size()))
                .body(dtos);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить профессию по ID",
        responses = {@ApiResponse(description = "Успешное получение профессии")})
    ProfessionDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать профессию",
        responses = {@ApiResponse(description = "Успешное создание профессии")})
    ProfessionDTO create(@Valid @RequestBody ProfessionCreateDTO data) {
        return service.create(data);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить профессию",
        responses = {@ApiResponse(description = "Успешное обновление профессии")})
    ProfessionDTO update(@PathVariable Long id, @Valid @RequestBody ProfessionUpdateDTO data) {
        return service.update(id, data);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удалить профессию",
        responses = {@ApiResponse(description = "Успешное удаление профессии")})
    void destroy(@PathVariable Long id) {
        service.delete(id);
    }

}
