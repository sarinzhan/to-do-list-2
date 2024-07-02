package com.example.todolist.mapper;

import com.example.todolist.dto.request.UpdateTaskRequestDto;
import com.example.todolist.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateTaskRequestMapper {
    Task dtoToEntity(UpdateTaskRequestDto dto);
}
