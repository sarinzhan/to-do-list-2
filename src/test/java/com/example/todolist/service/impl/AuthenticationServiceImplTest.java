package com.example.todolist.service.impl;

import com.example.todolist.dto.request.AuthenticateRequestDto;
import com.example.todolist.dto.response.AuthenticateResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.jwt.JwtHandler;
import com.example.todolist.service.UserService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtHandler jwtHandler;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    private User user;
    private String token;
    private AuthenticateRequestDto authenticateRequestDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("username");
        user.setPassword("password");

        token = "fdafkjl324";

        authenticateRequestDto = new AuthenticateRequestDto("username", "password");
    }

    @Test
    void testAuthenticateUser_validCredentials() {
        when(userService.loadUserByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        when(jwtHandler.generateToken(any(Authentication.class))).thenReturn(token);
        AuthenticateResponseDto response = authenticationService.authenticateUser(authenticateRequestDto);
        Assert.notNull(response);
        assertEquals(token, response.getToken());
    }

    @Test
    void testAuthenticateUser_invalidCredentials() {
        when(userService.loadUserByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(false);

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class,
                () -> authenticationService.authenticateUser(authenticateRequestDto));
        assertEquals("Ошибка авторизации", exception.getMessage());
    }
}