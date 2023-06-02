package com.example.todo_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class User {

    @Id
    @Column(name = "person_id")
    private int user_id;

    private String first_name;
    private String last_name;

    @Column(unique = true)
    private String email;
    private String phone;

    @Column(name = "dob")
    private LocalDate birthDate;

    @Column(unique = true)
    private String password;
}
