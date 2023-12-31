package ru.kata.spring.boot_security.demo.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.dto.UserResponse;
import ru.kata.spring.boot_security.demo.mapper.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor

class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        log.info("SBSDLOG: Получили всех пользователей");
        List<User> users = userRepository.findAll();
        log.debug("SBSDLOG: Getting all users:[{}]", users);
        return userMapper.toListDto(users);
    }

    @Override
    @Transactional
    public User saveUsers(UserRequest userRequest) {
//        userRequest.setRoles(List.of(1l,2l));
        User user = userMapper.toEntity(userRequest);
//        if (user.getRoles() == null) {
//            user.setRoles(Set.of(roleRepository.getById(2L)));
//        }
       return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);

        //userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        try {
            User users = userRepository.getById(id);
            return userMapper.toDto(users);
        }catch (Exception e){
            throw new UserNotFoundException("зщропривпао " + id);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }
}