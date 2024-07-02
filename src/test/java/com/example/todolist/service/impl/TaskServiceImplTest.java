package com.example.todolist.service.impl;

import com.example.todolist.entity.Task;
import com.example.todolist.exception.BaseBusinessLogicException;
import com.example.todolist.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        task1 = new Task();
        task1.setId(1L);
        task1.setDescription("Task 1");
        task1.setCompleted(false);

        task2 = new Task();
        task2.setId(2L);
        task2.setDescription("Task 2");
        task2.setCompleted(true);
    }

    @Test
    void testGetAllTasks_ok() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = taskService.getAll();

        assertEquals(2, tasks.size());
        assertEquals(task1.getDescription(), tasks.get(0).getDescription());
        assertEquals(task2.getDescription(), tasks.get(1).getDescription());
    }

    @Test
    void testGetAllTasks_noTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList());

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> taskService.getAll());
        assertEquals("Не удалось найти задач", exception.getMessage());
    }

    @Test
    void testGetTaskById_ok() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Task foundTask = taskService.getById(1L);

        assertNotNull(foundTask);
        assertEquals(task1.getDescription(), foundTask.getDescription());
    }

    @Test
    void testGetTaskById_notFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> taskService.getById(1L));
        assertEquals("Не удалось найти задачу", exception.getMessage());
    }

    @Test
    void testCreateTask_ok() {
        when(taskRepository.save(task1)).thenReturn(task1);

        assertDoesNotThrow(() -> taskService.create(task1));
    }

    @Test
    void testCreateTask_exception() {
        doThrow(new RuntimeException()).when(taskRepository).save(task1);

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> taskService.create(task1));
        assertEquals("Не удалось создать задачу", exception.getMessage());
    }

    @Test
    void testUpdateTask_ok() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(task1)).thenReturn(task1);

        assertDoesNotThrow(() -> taskService.update(task1));
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    void testUpdateTask_exception() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        doThrow(new RuntimeException()).when(taskRepository).save(task1);

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> taskService.update(task1));
        assertEquals("Не удалось обновить задачу", exception.getMessage());
    }

    @Test
    void testDeleteTask_ok() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task1));
        doNothing().when(taskRepository).deleteById(1L);

        assertDoesNotThrow(() -> taskService.delete(1L));
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTask_exception() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> taskService.delete(1L));
        assertEquals("Не удалось удалить задачу", exception.getMessage());
    }

}