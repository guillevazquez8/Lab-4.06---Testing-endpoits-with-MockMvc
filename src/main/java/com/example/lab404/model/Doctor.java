package com.example.lab404.model;

import javax.persistence.*;

@Entity
public class Doctor {

    @Id
    @Column(name="employee_id", nullable = false)
    private Long id;

    private String department;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ON_CALL', 'ON', 'OFF')")
    private Status status;

    public Doctor() {}

    public Long getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
