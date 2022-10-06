package com.example.lab406.service;

import com.example.lab406.dto.PatientDto;
import com.example.lab406.exceptions.BadRequestException;
import com.example.lab406.model.Doctor;
import com.example.lab406.model.Patient;
import com.example.lab406.repository.DoctorRepository;
import com.example.lab406.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    public Patient save(PatientDto patient) {
        Optional<Doctor> doctor = doctorRepository.findById(Long.valueOf(patient.getDoctorId()));
        if (doctor.isEmpty()) {
            throw new BadRequestException("El doctor con id " + patient.getDoctorId() + " no existe!");
        }
        Patient newPatient = new Patient();
        newPatient.setName(patient.getName());
        newPatient.setDateOfBirth(patient.getDateOfBirth());
        newPatient.setDoctor(doctor.get());

        return patientRepository.save(newPatient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient oldPatient, PatientDto newPatient) {
        oldPatient.setName(newPatient.getName());
        oldPatient.setDateOfBirth(newPatient.getDateOfBirth());
        Optional<Doctor> doctor = doctorRepository.findById(Long.valueOf(newPatient.getDoctorId()));
        oldPatient.setDoctor(doctor.get());

        return patientRepository.save(oldPatient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }




}
