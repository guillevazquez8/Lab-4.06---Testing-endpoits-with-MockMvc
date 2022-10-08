package com.example.lab406.repository;

import com.example.lab406.model.Patient;
import com.example.lab406.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p where p.dateOfBirth between ?1 and ?2")
    List<Patient> findByDateOfBirthBetween(LocalDate dateOfBirthStart, LocalDate dateOfBirthEnd);

    @Query("select p from Patient p where p.doctor.department = ?1")
    List<Patient> findByDoctor_Department(String department);

    @Query("select p from Patient p where p.doctor.status = ?1")
    List<Patient> findByDoctor_Status(Status status);
}
