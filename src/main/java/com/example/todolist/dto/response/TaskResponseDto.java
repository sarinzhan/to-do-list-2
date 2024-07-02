package com.example.todolist.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TaskResponseDto {

    private Long id;

    private String description;

    private Boolean isCompleted;

    public Long getId() {
        return id;
    }

    public TaskResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public TaskResponseDto setCompleted(Boolean completed) {
        isCompleted = completed;
        return this;
    }
}
