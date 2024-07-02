package com.example.todolist.service.impl;

import com.example.todolist.entity.User;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.repository.UserRepository;
import com.example.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        String password = user.getPassword();
        String encodePassword = passwordEncoder.encode(password);
        user.setPassword(encodePassword);
        try {
            userRepository.save(user);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось сохранить пользователя");
        }
    }

    @Override
    public User loadUserByUsername(String username){
        return userRepository.getByUsername(username)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти пользователя"));
    }
}
