package com.example.todo_application.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
public class TodoListDTO {

    private int list_id;

    @NotNull(message = "Title cannot be null")
    @Column(unique = true)
    private String title;

    @Size(max = 100)
    private String list_description;

    private UserDTO userDTO;

    @ToString.Exclude
    private List<TodoListDTO> todoListDTOS;
}
