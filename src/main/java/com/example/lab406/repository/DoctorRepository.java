package com.example.lab406.repository;

import com.example.lab406.model.Doctor;
import com.example.lab406.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByStatus(Status status);
    List<Doctor> findByDepartment(String department);

}
