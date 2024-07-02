package com.example.todolist.mapper;

import com.example.todolist.dto.response.TaskResponseDto;
import com.example.todolist.entity.Task;

import java.util.List;

public class TaskResponseMapper {
    public static TaskResponseDto entityToDto(Task entity){
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(entity.getId());
        dto.setCompleted(entity.getCompleted());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static List<TaskResponseDto> listEntityToListDto(List<Task> entities){
        return entities.stream()
                .map(TaskResponseMapper::entityToDto)
                .toList();
    }
}
