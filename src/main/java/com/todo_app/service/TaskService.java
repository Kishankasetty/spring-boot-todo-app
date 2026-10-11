package com.todo_app.service;

import com.todo_app.entity.Task;
import com.todo_app.repository.TaskRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	private final TaskRepository taskrepository;
	
	public TaskService(TaskRepository taskrepository) {
		this.taskrepository = taskrepository;
	}
	
	public List<Task> getAllTasks(){
		return taskrepository.findAll();
	}
	
	public Task createTask(Task task) {
		return taskrepository.save(task);
	}
	
	public Task getTaskById(Long id) {
		return taskrepository.findById(id).orElse(null);
	}
	
	public Task updateTask(long id, Task updatedTask) {
		
		Task existingTask = taskrepository.findById(id).orElse(null);
		
		if(existingTask ==null) {
			return null;
		}
		
		existingTask.setTitle(updatedTask.getTitle());
		existingTask.setCompleted(updatedTask.isCompleted());
		
		return taskrepository.save(existingTask);
	}
	
	public void deleteTask(long id){
		taskrepository.deleteById(id);
	}
	
}
