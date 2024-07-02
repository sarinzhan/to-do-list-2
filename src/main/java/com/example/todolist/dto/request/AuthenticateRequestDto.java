package com.example.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticateRequestDto {
    private String username;
    private String password;
}
