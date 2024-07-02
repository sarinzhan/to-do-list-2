package com.example.todolist.dto.request;

import com.example.todolist.entity.Task;


public class UpdateTaskRequestDto {
    private Long id;
    private String description;
    private Boolean isCompleted;

    public Long getId() {
        return id;
    }

    public UpdateTaskRequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateTaskRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public UpdateTaskRequestDto setCompleted(Boolean completed) {
        isCompleted = completed;
        return this;
    }
}
