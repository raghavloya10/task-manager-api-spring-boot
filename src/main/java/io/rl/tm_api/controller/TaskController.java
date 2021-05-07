package io.rl.tm_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.rl.tm_api.entity.Task;
import io.rl.tm_api.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/all") 
	public List<Task> fetchAllTasks() {
		return taskService.fetchAllTasks();
	}
	
	@GetMapping("/{id}")
	public Task fetchTask(@PathVariable int id) {
		return taskService.fetchTask(id);
	}
	
	@PostMapping("")
	public Task addTask(@RequestBody Task task) {
		task.setId(0);
		return taskService.addTask(task);
	}
	
	@PutMapping("")
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable int id) {
		taskService.deleteTask(id);
	}
    
}
