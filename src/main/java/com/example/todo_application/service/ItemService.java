package com.example.todo_application.service;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.entity.Item;
import com.example.todo_application.entity.ToDoList;
import com.example.todo_application.repository.ItemRepository;
import com.example.todo_application.repository.ListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ListRepository listRepository;

    public Integer addItemToList(ItemDTO itemDTO, Integer list_id) {
        Optional<ToDoList> optionalList = listRepository.findById(list_id);
        ToDoList existingList = optionalList.orElseThrow(() -> new RuntimeException("List does not exist"));
        Item newItem = Item.builder()
                .item_id(itemDTO.getItem_id())
                .item_description(itemDTO.getItem_description())
                .list(existingList)
                .build();

        itemRepository.save(newItem);
        return newItem.getItem_id();
    }
}
