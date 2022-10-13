package com.example.lab406;

import com.example.lab406.dto.PatientDto;
import com.example.lab406.model.Doctor;
import com.example.lab406.model.Patient;
import com.example.lab406.repository.DoctorRepository;
import com.example.lab406.repository.PatientRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.example.lab406.model.Status.*;
import static com.example.lab406.model.Status.OFF;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class PatientControllerTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Doctor doctor1 = new Doctor(356712L, "cardiology", "Alonso Flores", ON_CALL);
        Doctor doctor2 = new Doctor(564134L, "immunology", "Sam Ortega", ON);
        Doctor doctor3 = new Doctor(761527L, "cardiology", "German Ruiz", OFF);
        Doctor doctor4 = new Doctor(166552L, "pulmonary", "Maria Lin", ON);
        Doctor doctor5 = new Doctor(156545L, "orthopaedic", "Paolo Rodriguez", ON_CALL);
        Doctor doctor6 = new Doctor(172456L, "psychiatric", "John Paul Armes", OFF);
        List<Doctor> doctors = doctorRepository.saveAll(List.of(doctor1, doctor2, doctor3, doctor4, doctor5, doctor6));

        List<Patient> patients = patientRepository.saveAll(
                List.of(
                        new Patient(1L, "Elva Ginas", LocalDate.of(1995,9,28), doctor1),
                        new Patient(2L, "Joselito Vargas", LocalDate.of(1998,10,18), doctor3),
                        new Patient(3L, "Marvin Stuar", LocalDate.of(2005,1,1), doctor2),
                        new Patient(4L, "Chotacorta Martinez", LocalDate.of(1992,3,21), doctor5),
                        new Patient(5L, "Javier Maria de todos los Santos", LocalDate.of(1953,10,12), doctor6),
                        new Patient(6L, "Paco Mermelas", LocalDate.of(1985,6,27), doctor4)
                )
        );
    }
    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @Test
    void test_allPatients() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/patient"))
                .andExpect(
                        status().isOk())
                .andReturn();
        List<Patient> patientsResult = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Patient>>() { });
        var patients = patientRepository.findAll();

        assertEquals(patients.size(), patientsResult.size());
        patientsResult.sort(Comparator.comparing(Patient::getId));
        patients.sort(Comparator.comparing(Patient::getId));
        assertThat(patientsResult).usingRecursiveComparison()
                .isEqualTo(patients);
    }

    @Test
    void test_findById() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/patient/byId/2"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Vargas"));
    }

    @Test
    void test_findByDateOfBirth() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/patient/byDateOfBirth?dateStart=1995-01-01&dateFinish=1998-12-31"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("va Ginas"));
        assertFalse(result.getResponse().getContentAsString().contains("ElBichoSiuuu"));
    }

    @Test
    void test_findByDoctorDepartment() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/patient/byDoctorDepartment/psychiatric"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Santos"));
        assertFalse(result.getResponse().getContentAsString().contains("Chotacorta"));
    }

    @Test
    void test_findByDoctorStatusOff() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        get("/patient/byDoctorStatusOff"))
                .andExpect(
                        status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("OFF"));
        assertTrue(result.getResponse().getContentAsString().contains("Vargas"));
    }

    @Test
    void test_createPatient() throws Exception {
        Doctor doctor7 = new Doctor(94857L, "surgery", "Nick Riviera", OFF);
        doctorRepository.save(doctor7);
        mockMvc.perform(MockMvcRequestBuilders
                    .post("/patient")
                    .content(objectMapper.writeValueAsString(new PatientDto("Joselito Elpingas", LocalDate.of(1997,11,3), "94857")))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
        var patients = patientRepository.findAll();
        assertEquals(7, patients.size());
        assertTrue(patients.toString().contains("Elpingas"));
        assertFalse(patients.toString().contains("Siuuuu"));
    }

}
