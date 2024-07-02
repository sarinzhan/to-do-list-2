package com.example.todolist.mapper;

import com.example.todolist.dto.request.RegisterUserRequestDto;
import com.example.todolist.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserRequestMapper {

    User dtoToEntity(RegisterUserRequestDto dto);
}
