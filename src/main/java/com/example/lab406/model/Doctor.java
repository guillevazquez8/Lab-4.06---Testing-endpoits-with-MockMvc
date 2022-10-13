package com.example.lab406.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
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
