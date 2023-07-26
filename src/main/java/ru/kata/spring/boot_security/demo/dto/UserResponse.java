package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;
@Data
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private int age;
    private String username;
    private String email;

    private String roles;

}
