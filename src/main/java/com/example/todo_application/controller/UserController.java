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

import static com.example.todo_application.utility.Constants.NEW_USER_SUCCESSFULLY_CREATED_MESSAGE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO)
            throws UserAlreadyExistsException {
        userService.addUser(userDTO);
        return new SuccessResponseHandler().generateResponse(NEW_USER_SUCCESSFULLY_CREATED_MESSAGE, HttpStatus.CREATED);
    }
}
