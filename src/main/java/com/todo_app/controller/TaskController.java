package com.todo_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.todo_app.entity.Task;
import com.todo_app.service.TaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	public final TaskService taskservice;
	
	public TaskController(TaskService taskservice) {
		this.taskservice = taskservice;
	}
	
	@GetMapping
	public List<Task> getTasks() {
	      return taskservice.getAllTasks();
				
	}

	@PostMapping
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		
		Task savedTask = taskservice.createTask(task);
		
	return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable long id) {
		
		Task task = taskservice.getTaskById(id);
		
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@Valid @PathVariable long id,@RequestBody Task task) {
		
		Task updatetask = taskservice.updateTask(id, task);
		
		if(updatetask == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatetask);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable long id) {
		
		Task task = taskservice.getTaskById(id);
		
		if(task == null) {
			return ResponseEntity.notFound().build();
		}
		
		taskservice.deleteTask(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}



