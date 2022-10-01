package com.example.lab404.rest;

import com.example.lab404.model.Doctor;
import com.example.lab404.model.Patient;
import com.example.lab404.model.Status;
import com.example.lab404.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patient")
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @PostMapping("/patient")
    public Patient createPatient(@RequestBody @Valid Patient newPatient) {
        return patientRepository.save(newPatient);
    }

}
