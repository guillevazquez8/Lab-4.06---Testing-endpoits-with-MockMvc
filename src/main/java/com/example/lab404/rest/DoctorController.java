package com.example.lab404.rest;

import com.example.lab404.model.Doctor;
import com.example.lab404.repository.DoctorRepository;
import com.example.lab404.repository.PatientRepository;
import com.example.lab404.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor")
    public List<Doctor> allDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping("/doctor")
    public Doctor createDoctor(@RequestBody @Valid Doctor newDoctor) {
        return doctorRepository.save(newDoctor);
    }

    @PatchMapping("/doctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor updateStatus(@PathVariable Long id,
                               @RequestBody Doctor updateDoctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()) {
            return null;
        }
        Doctor doctor = doctorOptional.get();
        if (updateDoctor.getStatus() != null) {
            doctor.setStatus(updateDoctor.getStatus());
        }
        if (updateDoctor.getDepartment() != null) {
            doctor.setDepartment(updateDoctor.getDepartment());
        }
        return doctorRepository.save(doctor);
    }

}
