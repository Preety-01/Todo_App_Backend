package com.example.todo_application.controller;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.exceptions.*;
import com.example.todo_application.service.ItemService;
import com.example.todo_application.utility.SuccessResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.todo_application.utility.Constants.NEW_ITEM_SUCCESSFULLY_CREATED;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/lists/{list_id}/items")
    public ResponseEntity<Object> createTodoItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Integer list_id)
            throws ListDoesNotExistsException, ItemAlreadyExistsException {
        itemService.addItem(itemDTO, list_id);
        return new SuccessResponseHandler().generateResponse(NEW_ITEM_SUCCESSFULLY_CREATED, HttpStatus.CREATED);
    }

    @GetMapping(value = "/lists/{list_id}/items")
    public ResponseEntity<List<ItemDTO>> getAllTodoItemsUnderAList(@PathVariable Integer list_id)
            throws ListDoesNotExistsException, ItemNotFoundForListException {
        List<ItemDTO> fetchedItems = itemService.fetchAllTodoItems(list_id);
        return new ResponseEntity<>(fetchedItems, HttpStatus.OK);
    }
}
