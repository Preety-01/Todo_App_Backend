package com.example.todo_application.service;

import com.example.todo_application.dto.ItemDTO;
import com.example.todo_application.entity.Item;
import com.example.todo_application.entity.TodoList;
import com.example.todo_application.exceptions.ItemAlreadyExistsException;
import com.example.todo_application.exceptions.ItemNotFoundForListException;
import com.example.todo_application.exceptions.ListDoesNotExistsException;
import com.example.todo_application.repository.ItemRepository;
import com.example.todo_application.repository.ListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.todo_application.utility.Constants.*;

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
            throw new ItemAlreadyExistsException(ITEM_ALREADY_EXISTS);
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
                new ListDoesNotExistsException(LIST_DOES_NOT_EXIST));
        return existingList;
    }

    public List<ItemDTO> fetchAllTodoItems(Integer list_id) throws ListDoesNotExistsException,
            ItemNotFoundForListException {
        TodoList existingList = findExistingList(list_id);
        List<Item> items = existingList.getItems();
        if(items.isEmpty()){
            throw new ItemNotFoundForListException(NO_ITEMS_FOR_THIS_LIST);
        }
        List<ItemDTO> newItemDTOList = new ArrayList<>();
        items.forEach(item -> {
            ItemDTO itemDTO = ItemDTO.builder()
                    .item_id(item.getItem_id())
                    .item_description(item.getItem_description())
                    .build();
            newItemDTOList.add(itemDTO);
        });
        return newItemDTOList;
    }
}
