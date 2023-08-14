package ru.kata.spring.boot_security.demo.util;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserErrorResponse  {
    private String message;
    private long timestamp;



}
