package com.example.todo_application.controller;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.service.ListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping(value = "/lists")
    public Integer createTodoList(@Valid @RequestBody TodoListDTO todoListDTO) {
        return listService.addList(todoListDTO);
    }

}
