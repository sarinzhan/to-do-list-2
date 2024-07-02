package com.example.todolist.mapper;

import com.example.todolist.dto.request.UpdateTaskRequestDto;
import com.example.todolist.entity.Task;

public class UpdateTaskRequestMapper {
    public static Task dtoToEntity(UpdateTaskRequestDto dto){
        Task task = new Task();
        task.setId(dto.getId());
        task.setCompleted(dto.getCompleted());
        task.setDescription(dto.getDescription());
        return task;
    }
}
