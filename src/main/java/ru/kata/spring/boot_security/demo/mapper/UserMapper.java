package ru.kata.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.dto.UserResponse;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Mapper
public abstract class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    public List<UserResponse> toListDto(List<User> userList) {
        return null;
    }


    @Mapping(target = "roles", source = "user.roles", qualifiedByName = "rolesToString")
    public abstract UserResponse toDto(User user);

    @Mapping(target = "roles", source = "userRequest.roles", qualifiedByName = "longToRoles")
    public abstract User toEntity(UserRequest userRequest) ;



    @Named("rolesToString")
    public String rolesToString(Collection<Role> roles) {
        return roles.stream()
                .map(Object::toString)
                .collect(Collectors.joining(";"));
    }

    @Named("longToRoles")
    public Set<Role> longToRoles(List<Long> roles) {
        return roleRepository.findAllByIdIn(roles);

    }

}
