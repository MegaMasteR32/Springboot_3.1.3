package ru.kata.spring.boot_security.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserRequest {
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым!")
    private String name;
    private String surname;

    @Min(value = 0, message = "Поле не может быть меньше 0")
    private int age;
    private String username;

    @Email
    private String email;

    private LocalDateTime createdAt;

    private List<Long> roles;

    public String getUsername() {
        String[] str = getEmail().split("@");
        return str[0];
    }
}
