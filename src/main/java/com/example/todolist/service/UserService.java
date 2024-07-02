package com.example.todolist.service;

import com.example.todolist.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    void create(User user);

    User loadUserByUsername(String username);
}
