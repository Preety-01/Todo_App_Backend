package com.example.todo_application.service;

import com.example.todo_application.dto.UserDTO;
import com.example.todo_application.entity.User;
import com.example.todo_application.exceptions.UserAlreadyExistsException;
import com.example.todo_application.exceptions.UserTableEmptyException;
import com.example.todo_application.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.todo_application.utility.Constants.USER_ALREADY_EXISTS;
import static com.example.todo_application.utility.Constants.USER_TABLE_EMPTY;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDTO userDTO) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findById(userDTO.getUser_id());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        }
        User newUser = User.builder()
                .user_id(userDTO.getUser_id())
                .first_name(userDTO.getFirst_name())
                .last_name(userDTO.getLast_name())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .birthDate(userDTO.getBirthDate())
                .password(userDTO.getPassword())
                .build();
        userRepository.save(newUser);
    }

    public List<UserDTO> fetchAllUsers() throws UserTableEmptyException {
        List<User> allUsers = userRepository.findAll();
        if(allUsers.isEmpty()){
            throw new UserTableEmptyException(USER_TABLE_EMPTY);
        }
        List<UserDTO> fetchedUsersList = new ArrayList<>();
        allUsers.forEach(user -> {
            UserDTO userDTO = UserDTO.builder()
                    .user_id(user.getUser_id())
                    .first_name(user.getFirst_name())
                    .last_name(user.getLast_name())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .birthDate(user.getBirthDate())
                    .password(user.getPassword())
                    .build();
            fetchedUsersList.add(userDTO);
        });
        return fetchedUsersList;
    }
}
