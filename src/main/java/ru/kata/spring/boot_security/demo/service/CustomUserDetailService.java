//package ru.kata.spring.boot_security.demo.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.kata.spring.boot_security.demo.dao.UserDao;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repository.UserRepository;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final UserDao userDao;
//
//    @Autowired
//    public CustomUserDetailService(UserRepository userRepository, UserDao userDao) {
//
//        this.userRepository = userRepository;
//        this.userDao = userDao;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Пользователь не найден");
//        }
//        return user;
//                //new org.springframework.security.core.userdetails.User(
//                //user.getEmail(), user.getPassword(), user.getAuthorities());
//    }
//
//}
