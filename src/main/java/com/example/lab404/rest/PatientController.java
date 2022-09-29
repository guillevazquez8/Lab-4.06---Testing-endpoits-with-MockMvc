package com.example.lab404.rest;

import com.example.lab404.model.Patient;
import com.example.lab404.model.Status;
import com.example.lab404.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patient/byId/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    @GetMapping("/patient/byDateOfBirth")
    public List<Patient> getPatientsRangeDateOfBirth(@RequestParam String dateStart,
                                                     @RequestParam String dateFinish) {
        return patientRepository.findByDateOfBirthBetween(LocalDate.parse(dateStart), LocalDate.parse(dateFinish));
    }

    @GetMapping("/patient/byDoctorDepartment/{department}")
    public List<Patient> getPatientsByDoctorDepartment(@PathVariable String department) {
        return patientRepository.findByDoctor_Department(department);
    }

    @GetMapping("/patient/byDoctorStatusOff")
    public List<Patient> getPatientsByDoctorStatusOff() {
        return patientRepository.findByDoctor_Status(Status.OFF);
    }


}
