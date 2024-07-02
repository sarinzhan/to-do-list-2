package com.example.todolist.dto.request;


public class CreateTaskRequestDto {
    private String description;

    public String getDescription() {
        return description;
    }

    public CreateTaskRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
