package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


public interface RoleRepository {

    public List<Role> getAllRoles();

    public List<Role> findByIdRoles(List<Long> id);
}
