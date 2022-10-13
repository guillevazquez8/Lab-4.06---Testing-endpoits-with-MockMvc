package com.example.lab406.service;

import com.example.lab406.model.Doctor;
import com.example.lab406.model.Status;
import com.example.lab406.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    public List<Doctor> findByStatus(Status status) {
        return doctorRepository.findByStatus(status);
    }

    public List<Doctor> findByDepartment(String department) {return doctorRepository.findByDepartment(department);}

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

}
