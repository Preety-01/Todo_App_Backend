package com.example.todo_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class UserDTO {

    private int user_id;

    @NotNull(message = "First name can't be null")
    private String first_name;

    @NotNull(message = "Last name can't be null")
    private String last_name;

    @Email
    @NotNull(message = "Email can't be null")
    @Column(unique = true)
    private String email;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull(message = "Password can't be null")
    @Column(unique = true)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;

    @ToString.Exclude
    private List<TodoListDTO> todoListDTOS;
}
