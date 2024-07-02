package com.example.todolist.dto.request;

import lombok.Data;

@Data
public class RegisterUserRequestDto {

    private String username;
    private String password;
}
