package com.example.todolist.service.impl;

import com.example.todolist.entity.Task;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new BaseBusinessLogicException("Не удалось найти задач");
        }
        return tasks;
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти задачу"));
    }

    @Override
    public void create(Task task) {
        try{
            taskRepository.save(task);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось создать задачу");
        }
    }

    @Override
    public void update(Task task) {
        try{
            getById(task.getId());
            taskRepository.save(task);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось обновить задачу");
        }
    }

    @Override
    public void delete(Long id) {
        try{
            getById(id);
            taskRepository.deleteById(id);
        }catch (Exception ex){
            throw new BaseBusinessLogicException("Не удалось удалить задачу");
        }
    }
}
