package com.example.todolist.service.impl;

import com.example.todolist.entity.User;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setIsActive(true);
    }

    @Test
    void testCreateUser_ok() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("password");
        when(userRepository.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> userService.create(user));

        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testCreateUser_exception() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("password");
        when(userRepository.save(user)).thenThrow(new RuntimeException("Database error"));

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> userService.create(user));
        assertEquals("Не удалось сохранить пользователя", exception.getMessage());

        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLoadUserByUsername() {
        when(userRepository.getByUsername("testuser")).thenReturn(Optional.of(user));

        User loadedUser = userService.loadUserByUsername("testuser");

        assertNotNull(loadedUser);
        assertEquals(user.getUsername(), loadedUser.getUsername());
    }

    @Test
    void testLoadUserByUsername_notFound() {
        when(userRepository.getByUsername("nonexistent")).thenReturn(Optional.empty());

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> userService.loadUserByUsername("nonexistent"));
        assertEquals("Не удалось найти пользователя", exception.getMessage());
    }
}