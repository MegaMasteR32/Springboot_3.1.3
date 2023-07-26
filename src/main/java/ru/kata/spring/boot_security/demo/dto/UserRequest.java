package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String username;
    private String email;

    private List<Long> roles;

    public String getUsername() {
        String[] str = getEmail().split("@");
        return str[0];
    }
}
