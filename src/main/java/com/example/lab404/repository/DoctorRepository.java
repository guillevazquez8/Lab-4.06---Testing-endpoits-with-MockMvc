package com.example.lab404.repository;

import com.example.lab404.model.Doctor;
import com.example.lab404.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findById(Long id);
    List<Doctor> findByStatus(Status status);
    List<Doctor> findByDepartment(String department);
}
