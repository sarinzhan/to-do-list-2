package com.example.todolist.service.impl;

import com.example.todolist.dto.request.AuthenticateRequestDto;
import com.example.todolist.dto.response.AuthenticateResponseDto;
import com.example.todolist.entity.User;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.jwt.JwtHandler;
import com.example.todolist.service.AuthenticationService;
import com.example.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtHandler jwtHandler;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticateResponseDto authenticateUser(AuthenticateRequestDto authResponseDto) {
        AuthenticateResponseDto tokenResponse = new AuthenticateResponseDto();
        User user = userService.loadUserByUsername(authResponseDto.getUsername());
        if(passwordEncoder.matches(authResponseDto.getPassword(), user.getPassword())){
            Authentication auth = new UsernamePasswordAuthenticationToken(user, null, null);
            tokenResponse.setToken(jwtHandler.generateToken(auth));
            return tokenResponse;
        }else{
            throw new BaseBusinessLogicException("Ошибка авторизации");
        }
    }
}
