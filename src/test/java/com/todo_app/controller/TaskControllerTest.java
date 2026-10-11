
package com.todo_app.controller;

import com.todo_app.entity.Task;
import com.todo_app.service.TaskService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void shouldReturnTaskWhenIdExists() throws Exception {

        Task task = new Task("Learn Spring Boot", false);

        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title")
                .value("Learn Spring Boot"));
    }

    @Test
    void shouldReturn404WhenTaskDoesNotExist() throws Exception {

        when(taskService.getTaskById(999L)).thenReturn(null);

        mockMvc.perform(get("/api/tasks/999"))
            .andExpect(status().isNotFound());
    }
    

@Test
void shouldCreateTaskSuccessfully() throws Exception {

    Task savedTask = new Task("Learn JUnit", false);

    when(taskService.createTask(any(Task.class)))
        .thenReturn(savedTask);

    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Learn JUnit",
                        "completed": false,
                        "priority": "MEDIUM"
                    }
                    """)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value("Learn JUnit"));

    verify(taskService).createTask(any(Task.class));
}

@Test
void shouldRejectEmptyTaskTitle() throws Exception {

    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "",
                        "completed": false
                    }
                    """)
        )
        .andExpect(status().isBadRequest());

    verify(taskService, never()).createTask(any(Task.class));
}
}