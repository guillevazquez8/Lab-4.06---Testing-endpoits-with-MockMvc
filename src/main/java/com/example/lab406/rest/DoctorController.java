package com.example.lab406.rest;

import com.example.lab406.model.Doctor;
import com.example.lab406.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @GetMapping("/doctor")
    public List<Doctor> allDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.CREATED)
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
