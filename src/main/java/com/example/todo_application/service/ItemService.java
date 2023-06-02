package com.example.todo_application.service;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.entity.Item;
import com.example.todo_application.entity.TodoList;
import com.example.todo_application.exceptions.ItemAlreadyExistsException;
import com.example.todo_application.exceptions.ListDoesNotExistsException;
import com.example.todo_application.repository.ItemRepository;
import com.example.todo_application.repository.ListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.todo_application.utility.Constants.ITEM_ALREADY_EXISTS_MESSAGE;
import static com.example.todo_application.utility.Constants.LIST_DOES_NOT_EXIST_MESSAGE;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ListRepository listRepository;

    public void addItem(ItemDTO itemDTO, Integer list_id)
            throws ListDoesNotExistsException, ItemAlreadyExistsException {
        TodoList existingList = findExistingList(list_id);
        Optional<Item> existingItem = findExistingItem(itemDTO.getItem_id());
        if (existingItem.isPresent()) {
            throw new ItemAlreadyExistsException(ITEM_ALREADY_EXISTS_MESSAGE);
        }
        Item newItem = Item.builder()
                .item_id(itemDTO.getItem_id())
                .item_description(itemDTO.getItem_description())
                .list(existingList)
                .build();
        itemRepository.save(newItem);
    }

    private Optional<Item> findExistingItem(int item_id) {
        return itemRepository.findById(item_id);
    }

    private TodoList findExistingList(Integer list_id) throws ListDoesNotExistsException {
        Optional<TodoList> optionalList = listRepository.findById(list_id);
        TodoList existingList = optionalList.orElseThrow(() ->
                new ListDoesNotExistsException(LIST_DOES_NOT_EXIST_MESSAGE));
        return existingList;
    }
}
