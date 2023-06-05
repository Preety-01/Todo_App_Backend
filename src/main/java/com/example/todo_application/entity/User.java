package com.example.todo_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private List<TodoList> todoList = new ArrayList<>();
}
