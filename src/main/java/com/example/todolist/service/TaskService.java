package com.example.todolist.service;

import com.example.todolist.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task getById(Long id);
    void create(Task task);
    void update(Task task);
    void delete(Long id);
}
