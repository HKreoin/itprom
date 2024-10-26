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

import com.example.itprom.dto.profession.ProfessionDTO;
import com.example.itprom.mapper.ProfessionMapper;
import com.example.itprom.model.Profession;
import com.example.itprom.repository.ProfessionRepository;
import com.example.itprom.util.ModelGenerator;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProfessionMapper professionMapper;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private ModelGenerator modelGenerator;

    private Profession testProfession;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();

        testProfession = Instancio.of(modelGenerator.getProfessionModel())
                .create();
        professionRepository.save(testProfession);
    }

    @Test
    public void testIndex() throws Exception {
        var response = mockMvc.perform(get("/api/professions"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = response.getContentAsString();

        List<ProfessionDTO> professionDTOS = objectMapper.readValue(body, new TypeReference<>() { });

        var actual = professionDTOS.stream().map(professionMapper::map).toList();
        var expected = professionRepository.findAll();

        Assertions.assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testShow() throws Exception {
        var request = get("/api/professions/" + testProfession.getId());
        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        var body = result.getContentAsString();
        assertThatJson(body).and(
                v -> v.node("name").isEqualTo(testProfession.getName()));
    }

    @Test
    public void testCreate() throws Exception {
        var data = Instancio.of(modelGenerator
                        .getProfessionModel())
                        .create();

        var request = post("/api/professions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var profession =  professionRepository.findByName(data.getName()).get();

        assertNotNull(profession);
        assertEquals(profession.getName(), data.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        var id = testProfession.getId();

        var data = new HashMap<>();
        data.put("name", "Test profession name");

        var request = put("/api/professions/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var profession = professionRepository.findById(id).get();

        assertThat(profession.getName()).isEqualTo("Test profession name");
    }

    @Test
    public void testDelete() throws Exception {
        var id = testProfession.getId();
        var request = delete("/api/professions/" + id);
        mockMvc.perform(request);
        assertThat(professionRepository.findById(id).isEmpty()).isTrue();
    }

}
