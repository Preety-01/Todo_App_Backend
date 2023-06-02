package com.example.todo_application.service;

import com.example.todo_application.dto.TodoListDTO;
import com.example.todo_application.entity.TodoList;
import com.example.todo_application.entity.User;
import com.example.todo_application.exceptions.ListAlreadyExistsException;
import com.example.todo_application.exceptions.UserDoesNotExistsException;
import com.example.todo_application.repository.ListRepository;
import com.example.todo_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.todo_application.utility.Constants.LIST_ALREADY_EXISTS_MESSAGE;
import static com.example.todo_application.utility.Constants.USER_DOES_NOT_EXIST_MESSAGE;

@Service
@Transactional
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private UserRepository userRepository;

    public void addList(TodoListDTO listDTO, Integer user_id)
            throws UserDoesNotExistsException, ListAlreadyExistsException {

        User existingUser = findExistingUser(user_id);
        Optional<TodoList> optionalTodoList = findExistingList(listDTO.getList_id());
        if(optionalTodoList.isPresent()){
            throw new ListAlreadyExistsException(LIST_ALREADY_EXISTS_MESSAGE);
        }
        TodoList newList = TodoList.builder()
                .list_id(listDTO.getList_id())
                .title(listDTO.getTitle())
                .list_description(listDTO.getList_description())
                .user(existingUser)
                .build();
        listRepository.save(newList);
    }

    private Optional<TodoList> findExistingList(int list_id) {
        return listRepository.findById(list_id);
    }

    private User findExistingUser(Integer user_id) throws UserDoesNotExistsException {
        Optional<User> optionalUser = userRepository.findById(user_id);
        User existingUser = optionalUser.orElseThrow(() -> new UserDoesNotExistsException(USER_DOES_NOT_EXIST_MESSAGE));
        return existingUser;
    }
}
