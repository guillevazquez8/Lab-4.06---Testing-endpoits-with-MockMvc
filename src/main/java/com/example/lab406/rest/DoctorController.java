package com.example.lab406.rest;

import com.example.lab406.model.Doctor;
import com.example.lab406.model.Status;
import com.example.lab406.repository.DoctorRepository;
import com.example.lab406.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("doctor")
@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<Doctor> allDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("byId/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.findById(id);
    }

    @GetMapping("byStatus/{status}")
    public List<Doctor> getDoctorsByStatus(@PathVariable Status status) {
        return doctorService.findByStatus(status);
    }

    @GetMapping("byDepartment/{department}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable String department) {
        return doctorService.findByDepartment(department);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor createDoctor(@RequestBody @Valid Doctor newDoctor) {
        return doctorService.save(newDoctor);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor updateStatus(@PathVariable Long id,
                               @RequestBody Doctor updateDoctor) {
        Optional<Doctor> doctorOptional = doctorService.findById(id);
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
        return doctorService.save(doctor);
    }

}
