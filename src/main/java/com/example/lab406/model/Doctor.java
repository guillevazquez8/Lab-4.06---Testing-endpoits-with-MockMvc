package com.example.lab406.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @Column(name="employee_id", nullable = false)
    private Long id;

    private String department;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ON_CALL', 'ON', 'OFF')")
    private Status status;

}
