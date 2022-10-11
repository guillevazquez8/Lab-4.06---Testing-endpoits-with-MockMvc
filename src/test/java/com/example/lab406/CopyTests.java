/*package com.example.lab406;

import com.example.lab406.model.Doctor;
import com.example.lab406.repository.DoctorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.example.lab406.model.Status.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class CopyTests {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;


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
        doctorRepository.deleteAll();
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/doctors/356712"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Alonso Flores"));
    }

    @Test
    void findByIdNotFound() throws Exception {
        String doctorId = "35";
        MvcResult result = mockMvc
                .perform(
                        get("/doctors/"+doctorId))
                .andExpect(
                        status().isNotFound())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("El doctor con id "+ doctorId + " no se ha encontrado."));
    }

    @Test
    void shouldSaveDoctor() throws Exception {

        Doctor doctor = new Doctor();
        doctor.setEmployeeId("123");
        doctor.setName("Jesus Benito");
        doctor.setDepartment("Cardiology");
        doctor.setStatus(EmployeeStatus.ON);
        ResultActions resultActions = mockMvc.perform(post("/doctors")
                        .content(objectMapper.writeValueAsString(doctor))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Doctor doctorCreated = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Doctor.class);

        assertEquals(doctor, doctorCreated);
    }
}*/
