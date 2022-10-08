package com.example.lab406.rest;

import com.example.lab406.dto.PatientDto;
import com.example.lab406.exceptions.BadRequestException;
import com.example.lab406.model.Patient;
import com.example.lab406.model.Status;
import com.example.lab406.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/patient")
    public List<Patient> allPatients() {
        return patientService.findAll();
    }

    @GetMapping("/patient/byId/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @GetMapping("/patient/byDateOfBirth")
    public List<Patient> getPatientsRangeDateOfBirth(@RequestParam String dateStart,
                                                     @RequestParam String dateFinish) {
        return patientService.findByDateOfBirthBetween(LocalDate.parse(dateStart), LocalDate.parse(dateFinish));
    }

    @GetMapping("/patient/byDoctorDepartment/{department}")
    public List<Patient> getPatientsByDoctorDepartment(@PathVariable String department) {
        return patientService.findByDoctor_Department(department);
    }

    @GetMapping("/patient/byDoctorStatusOff")
    public List<Patient> getPatientsByDoctorStatusOff() {
        return patientService.findByDoctor_Status(Status.OFF);
    }

    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody PatientDto newPatient) {
        return patientService.save(newPatient);
    }

    @PutMapping("/patient/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable Long id, @RequestBody PatientDto updatePatient) {
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()) {
            throw new BadRequestException("The introduced id " + id + " doesn't belong to any patient");
        }
        Patient patient = patientOptional.get();

        return patientService.update(patient, updatePatient);
    }

}
