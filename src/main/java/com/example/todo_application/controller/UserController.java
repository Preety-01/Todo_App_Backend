package com.example.todo_application.controller;

import com.example.todo_application.dto.UserDTO;
import com.example.todo_application.exceptions.UserAlreadyExistsException;
import com.example.todo_application.service.UserService;
import com.example.todo_application.utility.SuccessResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistsException {
        Integer newUserId = userService.addUser(userDTO);
        String successMessage = "User - " + newUserId + " successfully created";
        return new SuccessResponseHandler().generateResponse(successMessage, HttpStatus.CREATED);
    }
}
