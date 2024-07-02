package com.example.todolist.controller;


import com.example.todolist.dto.request.AuthenticateRequestDto;
import com.example.todolist.dto.request.RegisterUserRequestDto;
import com.example.todolist.dto.response.AuthenticateResponseDto;
import com.example.todolist.dto.response.CommonResponseDto;
import com.example.todolist.mapper.RegisterUserRequestMapper;
import com.example.todolist.service.AuthenticationService;
import com.example.todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@Tag(name = "User API")
public class UserController {
    private final AuthenticationService authenticateService;
    private final UserService userService;

    private final RegisterUserRequestMapper registerUserRequestMapper;

    @Operation(summary = "Register user")
    @PostMapping("/register")
    public CommonResponseDto<Long> createUser(
            @RequestBody RegisterUserRequestDto registerUserRequestDto
    ){
        userService.create(
                registerUserRequestMapper.dtoToEntity(registerUserRequestDto)
        );
        return new CommonResponseDto<Long>()
                .setOk();
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/auth")
    public CommonResponseDto<AuthenticateResponseDto> authUser(
            @RequestBody AuthenticateRequestDto authenticateRequestDto
    ){
        return new CommonResponseDto<AuthenticateResponseDto>()
                .setOk()
                .setData(
                        authenticateService.authenticateUser(authenticateRequestDto)
                );
    }
}
