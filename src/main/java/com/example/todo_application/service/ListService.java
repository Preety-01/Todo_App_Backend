package com.example.todo_application.service;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.entity.TodoList;
import com.example.todo_application.entity.User;
import com.example.todo_application.repository.ListRepository;
import com.example.todo_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserRepository userRepository;

    public Integer addList(TodoListDTO listDTO, Integer user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        User existingUser = optionalUser.orElseThrow(() -> new RuntimeException("User does not exist"));
        TodoList newList = TodoList.builder()
                .list_id(listDTO.getList_id())
                .title(listDTO.getTitle())
                .list_description(listDTO.getList_description())
                .user(existingUser)
                .build();
        listRepository.save(newList);
        return newList.getList_id();
    }
}
