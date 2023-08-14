package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.dto.UserResponse;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserErrorResponse;
import ru.kata.spring.boot_security.demo.util.UserNotCreatedException;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @GetMapping()
    public List<UserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUsById(@PathVariable("id") long id) {
        UserResponse userResponse = userService.getById(id);

        if (userResponse == null) {
            throw new UserNotFoundException("зщропривпао " + id);
        }
        return userResponse;
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("hgfhnfh", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserRequest userRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
        throw new UserNotCreatedException(errorMessage.toString());
        }

        userService.saveUsers(userRequest);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
