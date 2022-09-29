package com.example.lab404.rest;

import com.example.lab404.model.Doctor;
import com.example.lab404.model.Status;
import com.example.lab404.repository.DoctorRepository;
import com.example.lab404.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/doctor")
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/doctor/byId/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorRepository.findById(id);
    }

    @GetMapping("/doctor/byStatus")
    public List<Doctor> getDoctorsByStatus(@RequestParam Status status) {
        return doctorRepository.findByStatus(status);
    }

    @GetMapping("/doctor/byDepartment/{department}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable String department) {
        return doctorRepository.findByDepartment(department);
    }
}
