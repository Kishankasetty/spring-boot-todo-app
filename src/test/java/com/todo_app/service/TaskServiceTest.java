
package com.todo_app.service;

import com.todo_app.entity.Task;
import com.todo_app.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() {

        List<Task> tasks = List.of(
            new Task("Learn Java", false),
            new Task("Learn Spring Boot", false)
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Learn Java", result.get(0).getTitle());

        verify(taskRepository).findAll();
    }
    

@Test
void shouldCreateTask() {
    Task task = new Task("Learn JUnit", false);

    when(taskRepository.save(task)).thenReturn(task);

    Task result = taskService.createTask(task);

    assertEquals("Learn JUnit", result.getTitle());
    verify(taskRepository).save(task);
}

@Test
void shouldReturnTaskWhenIdExists() {
    Task task = new Task("Learn Mockito", false);

    when(taskRepository.findById(1L))
        .thenReturn(Optional.of(task));

    Task result = taskService.getTaskById(1L);

    assertEquals("Learn Mockito", result.getTitle());
    verify(taskRepository).findById(1L);
}

@Test
void shouldReturnNullWhenTaskDoesNotExist() {
    when(taskRepository.findById(99L))
        .thenReturn(Optional.empty());

    Task result = taskService.getTaskById(99L);

    assertNull(result);
    verify(taskRepository).findById(99L);
}

@Test
void shouldUpdateExistingTask() {
    Task existingTask = new Task("Old title", false);
    Task updatedTask = new Task("New title", true);

    when(taskRepository.findById(1L))
        .thenReturn(Optional.of(existingTask));

    when(taskRepository.save(any(Task.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    Task result = taskService.updateTask(1L, updatedTask);

    assertEquals("New title", result.getTitle());
    assertEquals(true, result.isCompleted());

    verify(taskRepository).save(existingTask);
}
}