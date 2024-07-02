package com.example.todolist.controller;

import com.example.todolist.dto.request.CreateTaskRequestDto;
import com.example.todolist.dto.request.UpdateTaskRequestDto;
import com.example.todolist.dto.response.CommonResponseDto;
import com.example.todolist.dto.response.TaskResponseDto;
import com.example.todolist.entity.Task;
import com.example.todolist.mapper.CreateTaskRequestMapper;
import com.example.todolist.mapper.TaskResponseMapper;
import com.example.todolist.mapper.UpdateTaskRequestMapper;
import com.example.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Task API")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all tasks")
    public CommonResponseDto<List<TaskResponseDto>> getAll(){
        List<Task> tasks = taskService.getAll();
        List<TaskResponseDto> taskResponseDtos = TaskResponseMapper.listEntityToListDto(tasks);
        return new CommonResponseDto<List<TaskResponseDto>>()
                .setData(taskResponseDtos)
                .setOk();
    }

    @GetMapping("/get-by-id")
    @Operation(summary = "Get task by id")
    public CommonResponseDto<TaskResponseDto> getById(
            @RequestParam Long id
    ){
        Task task = taskService.getById(id);
        TaskResponseDto taskResponseDto = TaskResponseMapper.entityToDto(task);
        return new CommonResponseDto<TaskResponseDto>()
                .setData(taskResponseDto)
                .setOk();
    }

    @PostMapping("/add")
    @Operation(summary = "Add task")
    public CommonResponseDto<Void> add(
            @RequestBody CreateTaskRequestDto createTaskRequestDto
    ){
        Task task = CreateTaskRequestMapper.dtoToEntity(createTaskRequestDto);
        taskService.create(task);
        return new CommonResponseDto<Void>()
                .setOk();
    }

    @PostMapping("/update")
    @Operation(summary = "Update task")
    public CommonResponseDto<Void> update(
            @RequestBody UpdateTaskRequestDto dto
    ){
        Task task = UpdateTaskRequestMapper.dtoToEntity(dto);
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
