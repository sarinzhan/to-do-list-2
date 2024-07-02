package com.example.todolist.mapper;

import com.example.todolist.dto.request.CreateTaskRequestDto;
import com.example.todolist.entity.Task;

public class CreateTaskRequestMapper {

    public static Task dtoToEntity(CreateTaskRequestDto dto){
        Task task = new Task();
        task.setDescription(dto.getDescription());
        return task;
    }
}
