package com.example.todolist.service;

import com.example.todolist.dto.request.AuthenticateRequestDto;
import com.example.todolist.dto.response.AuthenticateResponseDto;

public interface AuthenticationService {
    AuthenticateResponseDto authenticateUser(AuthenticateRequestDto authResponseDto);
}
