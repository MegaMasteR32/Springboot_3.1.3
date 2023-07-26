package ru.kata.spring.boot_security.demo.mapper;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.dto.UserResponse;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;

    public List<UserResponse> toListDto(List<User> userList) {
        return userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public UserResponse toDto(User entity) {
        var dto = new UserResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());

        var roles = entity.getRoles().stream()
                .map(Role::getRole)
                .reduce((x, y) -> x + ", " + y);
        dto.setRoles(roles.orElse("==="));

        return dto;

    }

    public User toEntity(UserRequest userRequest) {
        var user = new User();
        user.setId(userRequest.getId());
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setRoles(roleRepository.findAllByIdIn(userRequest.getRoles()));




        return user;
    }
}
