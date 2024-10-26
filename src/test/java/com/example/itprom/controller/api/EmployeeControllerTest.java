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

import com.example.itprom.dto.employee.EmployeeDTO;
import com.example.itprom.mapper.EmployeeMapper;
import com.example.itprom.model.Employee;
import com.example.itprom.repository.EmployeeRepository;
import com.example.itprom.util.ModelGenerator;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelGenerator modelGenerator;

    private Employee testEmployee;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();

        testEmployee = Instancio.of(modelGenerator.getEmployeeModel())
                .create();
        employeeRepository.save(testEmployee);
    }

    @Test
    public void testIndex() throws Exception {
        var response = mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = response.getContentAsString();

        List<EmployeeDTO> employeeDTOS = objectMapper.readValue(body, new TypeReference<>() { });

        var actual = employeeDTOS.stream().map(employeeMapper::map).toList();
        var expected = employeeRepository.findAll();

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testShow() throws Exception {
        var request = get("/api/employees/" + testEmployee.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = result.getContentAsString();
        assertThatJson(body).and(
                v -> v.node("full_name").isEqualTo(testEmployee.getFullName()));
    }

    @Test
    public void testCreate() throws Exception {
        var data = Instancio.of(modelGenerator
                        .getEmployeeModel())
                        .create();

        var request = post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var employee =  employeeRepository.findByFullName(data.getFullName()).get();

        assertNotNull(employee);
        assertEquals(employee.getFullName(), data.getFullName());
    }

    @Test
    public void testUpdate() throws Exception {
        var id = testEmployee.getId();

        var data = new HashMap<>();
        data.put("full_name", "Test employee name");

        var request = put("/api/employees/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var Employee = employeeRepository.findById(id).get();

        assertThat(Employee.getFullName()).isEqualTo("Test employee name");
    }

    @Test
    public void testDelete() throws Exception {
        var id = testEmployee.getId();
        var request = delete("/api/employees/" + id);
        mockMvc.perform(request);
        assertThat(employeeRepository.findById(id).isEmpty()).isTrue();
    }

}
