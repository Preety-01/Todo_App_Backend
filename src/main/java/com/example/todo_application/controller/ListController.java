package com.example.todo_application.controller;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.service.ListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping(value = "/users/{user_id}/lists")
    public Integer createTodoList(@Valid @RequestBody TodoListDTO listDTO, @PathVariable Integer user_id) {
        return listService.addList(listDTO, user_id);
    }

}
