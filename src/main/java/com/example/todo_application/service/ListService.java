package com.example.todo_application.service;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.entity.ToDoList;
import com.example.todo_application.repository.ListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ListService {

    @Autowired
    private ListRepository listRepository;

    public Integer addList(TodoListDTO todoListDTO) {
        ToDoList newList = ToDoList.builder()
                .list_id(todoListDTO.getList_id())
                .title(todoListDTO.getTitle())
                .list_description(todoListDTO.getList_description())
                .build();
        listRepository.save(newList);
        return newList.getList_id();
    }
}
