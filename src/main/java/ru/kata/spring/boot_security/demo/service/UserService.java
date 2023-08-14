package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.dto.UserResponse;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    User saveUsers(UserRequest userRequest);

    void updateUser(UserRequest userRequest);

    UserResponse getById(Long id);

    void delete(Long id);

    User findByUsername(String username);

}
