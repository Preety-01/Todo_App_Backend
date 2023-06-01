package com.example.todo_application.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {

    private int item_id;

    @Size(max = 100)
    private String item_description;

    private TodoListDTO listDTO;
}
