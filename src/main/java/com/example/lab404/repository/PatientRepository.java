package com.example.lab404.repository;

import com.example.lab404.model.Patient;
import com.example.lab404.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findById(Long id);

    @Query("select p from Patient p where p.dateOfBirth between ?1 and ?2")
    List<Patient> findByDateOfBirthBetween(LocalDate dateOfBirthStart, LocalDate dateOfBirthEnd);

    @Query("select p from Patient p where p.doctor.department = ?1")
    List<Patient> findByDoctor_Department(String department);

    @Query("select p from Patient p where p.doctor.status = ?1")
    List<Patient> findByDoctor_Status(Status status);



}
