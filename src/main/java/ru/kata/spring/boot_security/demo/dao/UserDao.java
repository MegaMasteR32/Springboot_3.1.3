package ru.kata.spring.boot_security.demo.dao;





import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void delete(Long id);

    User getById(Long id);

    List<User> getAllUsers();


    User findByUsername(String username);


    void updateUser(User user);
}

