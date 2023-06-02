package com.example.todo_application.controller;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.exceptions.ItemAlreadyExistsException;
import com.example.todo_application.exceptions.ListDoesNotExistsException;
import com.example.todo_application.service.ItemService;
import com.example.todo_application.utility.SuccessResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.todo_application.utility.Constants.NEW_ITEM_SUCCESSFULLY_CREATED_MESSAGE;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/lists/{list_id}/items")
    public ResponseEntity<Object> createTodoItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Integer list_id)
            throws ListDoesNotExistsException, ItemAlreadyExistsException {
        itemService.addItem(itemDTO, list_id);
        return new SuccessResponseHandler().generateResponse(NEW_ITEM_SUCCESSFULLY_CREATED_MESSAGE, HttpStatus.CREATED);
    }
}
