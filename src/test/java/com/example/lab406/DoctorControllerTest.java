package com.example.lab406;

import com.example.lab406.model.Doctor;
import com.example.lab406.repository.DoctorRepository;
import com.example.lab406.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.example.lab406.model.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DoctorControllerTest {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Doctor> doctors = doctorRepository.saveAll(
                List.of(
                        new Doctor(356712L, "cardiology", "Alonso Flores", ON_CALL),
                        new Doctor(564134L, "immunology", "Sam Ortega", ON),
                        new Doctor(761527L, "cardiology", "German Ruiz", OFF),
                        new Doctor(166552L, "pulmonary", "Maria Lin", ON),
                        new Doctor(156545L, "orthopaedic", "Paolo Rodriguez", ON_CALL),
                        new Doctor(172456L, "psychiatric", "John Paul Armes", OFF)
                )
        );
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @Test
    void test_findById() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/doctor/byId/356712"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Alonso Flores"));
    }

    @Test
    void test_findByStatus() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/doctor/byStatus/OFF"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("OFF"));
        assertTrue(result.getResponse().getContentAsString().contains("German Ruiz"));
        assertFalse(result.getResponse().getContentAsString().contains("ON"));
    }

    @Test
    void test_findByDepartment() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/doctor/byDepartment/psychiatric"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("John Paul"));
        assertFalse(result.getResponse().getContentAsString().contains("ElBichoSiuuu"));
    }

    @Test
    void test_createDoctor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/doctor")
                        .content(objectMapper.writeValueAsString(new Doctor(43312L, "psychology", "Zalo Soliño", ON_CALL)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        /*Doctor newDoctor = new Doctor();
        newDoctor.setId(43312L);
        newDoctor.setDepartment("psychology");
        newDoctor.setName("Zalo Soliño");
        newDoctor.setStatus(ON_CALL);
        ResultActions resultsActions = mockMvc
                .perform(post("/doctor")
                        .content(objectMapper.writeValueAsString(newDoctor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Doctor fakeDoctor = objectMapper.readValue(resultsActions.andReturn().getResponse().getContentAsString(), Doctor.class);

        assertEquals(newDoctor, fakeDoctor);*/
    }

}
