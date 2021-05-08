package io.rl.tm_api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rl.tm_api.entity.Task;
import io.rl.tm_api.entity.User;
import io.rl.tm_api.jpa.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskRepository taskRepository;
		
	@Transactional
	public List<Task> fetchAllTasks() {
		User user = userService.fetchMyDetails();
		return taskRepository.findAllByUser(user);
	}
	
	@Transactional
	public Task fetchTask(int id) {
		return taskRepository.findById(id).get();
	}
	
	@Transactional
	public Task addTask(Task task) {
		User user = userService.fetchMyDetails();
		task.setUser(user);
		return taskRepository.save(task);
	}
	
	@Transactional
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	@Transactional
	public void deleteTask(int id) {
		taskRepository.deleteById(id);;
	}

}
