package com.example.todolist.controller;

import com.example.todolist.dto.request.CreateTaskRequestDto;
import com.example.todolist.dto.request.UpdateTaskRequestDto;
import com.example.todolist.dto.response.CommonResponseDto;
import com.example.todolist.entity.Task;
import com.example.todolist.mapper.CreateTaskRequestMapper;
import com.example.todolist.mapper.UpdateTaskRequestMapper;
import com.example.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
@Tag(name = "Task API")
public class TaskController {

    private final TaskService taskService;

    private final CreateTaskRequestMapper createTaskRequestMapper;
    private final UpdateTaskRequestMapper updateTaskRequestMapper;

    @GetMapping("/get-all")
    @Operation(summary = "Get all tasks")
    public CommonResponseDto<List<Task>> getAll(){
        List<Task> tasks = taskService.getAll();
        return new CommonResponseDto<List<Task>>()
                .setData(tasks)
                .setOk();
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "Get task by id")
    public CommonResponseDto<Task> getById(
            @RequestParam Long id
    ){
        Task task = taskService.getById(id);
        return new CommonResponseDto<Task>()
                .setData(task)
                .setOk();
    }

    @PostMapping("/add")
    @Operation(summary = "Add task")
    public CommonResponseDto<Void> add(
            @RequestBody CreateTaskRequestDto createTaskRequestDto
    ){
        Task task = createTaskRequestMapper.dtoToEntity(createTaskRequestDto);
        taskService.create(task);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @PostMapping("/update")
    @Operation(summary = "Update task")
    public CommonResponseDto<Void> update(
            @RequestBody UpdateTaskRequestDto dto
    ){
        Task task = updateTaskRequestMapper.dtoToEntity(dto);
        taskService.update(task);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @DeleteMapping("/remove")
    @Operation(summary = "Remove task")
    public CommonResponseDto<Void> remove(
            @RequestParam Long id
    ){
        taskService.delete(id);
        return new CommonResponseDto<Void>()
                .setOk();
    }
}
