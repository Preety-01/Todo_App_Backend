package com.example.todo_application.controller;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.exceptions.ListAlreadyExistsException;
import com.example.todo_application.exceptions.UserDoesNotExistsException;
import com.example.todo_application.service.ListService;
import com.example.todo_application.utility.SuccessResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.todo_application.utility.Constants.NEW_LIST_SUCCESSFULLY_CREATED_MESSAGE;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping(value = "/users/{user_id}/lists")
    public ResponseEntity<Object> createTodoList(@Valid @RequestBody TodoListDTO listDTO, @PathVariable Integer user_id)
            throws UserDoesNotExistsException, ListAlreadyExistsException {
        listService.addList(listDTO, user_id);
        return new SuccessResponseHandler().generateResponse(NEW_LIST_SUCCESSFULLY_CREATED_MESSAGE, HttpStatus.CREATED);
    }

}
