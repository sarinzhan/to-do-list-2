package com.example.todolist.mapper;

import com.example.todolist.dto.request.CreateTaskRequestDto;
import com.example.todolist.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CreateTaskRequestMapper {

    Task dtoToEntity(CreateTaskRequestDto dto);
}
