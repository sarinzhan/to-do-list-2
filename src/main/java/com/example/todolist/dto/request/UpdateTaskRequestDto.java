package com.example.todolist.dto.request;

import com.example.todolist.entity.Task;
import lombok.Data;
import org.mapstruct.Mapper;


@Data
public class UpdateTaskRequestDto {
    private Long id;
    private String description;
    private Boolean isCompleted;
}
