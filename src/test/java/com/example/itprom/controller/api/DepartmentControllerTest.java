package com.example.itprom.controller.api;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.itprom.dto.department.DepartmentDTO;
import com.example.itprom.mapper.DepartmentMapper;
import com.example.itprom.model.Department;
import com.example.itprom.repository.DepartmentRepository;
import com.example.itprom.util.ModelGenerator;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelGenerator modelGenerator;

    private Department testDepartment;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();

        testDepartment = Instancio.of(modelGenerator.getDepartmentModel())
                .create();
        departmentRepository.save(testDepartment);
    }

    @Test
    public void testIndex() throws Exception {
        var response = mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = response.getContentAsString();

        List<DepartmentDTO> departmentDTOS = objectMapper.readValue(body, new TypeReference<>() { });

        var actual = departmentDTOS.stream().map(departmentMapper::map).toList();
        var expected = departmentRepository.findAll();

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testShow() throws Exception {
        var request = get("/api/departments/" + testDepartment.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = result.getContentAsString();
        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(testDepartment.getName()));
    }

    @Test
    public void testCreate() throws Exception {
        var data = Instancio.of(modelGenerator
                        .getDepartmentModel())
                        .create();

        var request = post("/api/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var department =  departmentRepository.findByName(data.getName()).get();

        assertNotNull(department);
        assertEquals(department.getName(), data.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        var id = testDepartment.getId();

        var data = new HashMap<>();
        data.put("name", "Test department name");

        var request = put("/api/departments/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var department = departmentRepository.findById(id).get();

        assertThat(department.getName()).isEqualTo("Test department name");
    }

    @Test
    public void testDelete() throws Exception {
        var id = testDepartment.getId();
        var request = delete("/api/departments/" + id);
        mockMvc.perform(request);
        assertThat(departmentRepository.findById(id).isEmpty()).isTrue();
    }

}
