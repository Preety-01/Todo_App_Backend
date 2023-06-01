package com.example.todo_application.controller;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(value = "/lists/{list_id}/items")
    public Integer createTodoItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Integer list_id) {
        return itemService.addItemToList(itemDTO, list_id);
    }
}
