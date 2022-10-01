package com.example.lab404.rest;

import com.example.lab404.model.Doctor;
import com.example.lab404.model.Patient;
import com.example.lab404.model.Status;
import com.example.lab404.repository.PatientRepository;
import com.example.lab404.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient")
    public List<Patient> allPatients() {
        return patientRepository.findAll();
    }

    @PostMapping("/patient")
    public Patient createPatient(@RequestBody @Valid Patient newPatient) {
        return patientRepository.save(newPatient);
    }

    @PutMapping("/patient/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable Long id, @RequestBody @Valid Patient updatePatient) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {return null;}
        Patient patient = patientOptional.get();
        return patientRepository.save(updatePatient);
    }

}
