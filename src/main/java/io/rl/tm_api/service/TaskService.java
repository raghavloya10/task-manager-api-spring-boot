package io.rl.tm_api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rl.tm_api.dao.TaskDAO;
import io.rl.tm_api.entity.Task;

@Service
public class TaskService {
	
	@Autowired
	private TaskDAO taskDAO;
		
	@Transactional
	public List<Task> fetchAllTasks() {
		return taskDAO.fetchAllTasks();
	}
	
	@Transactional
	public Task fetchTask(int id) {
		return taskDAO.fetchTask(id);
	}
	
	@Transactional
	public Task addTask(Task task) {
		return taskDAO.save(task);
	}
	
	@Transactional
	public Task updateTask(Task task) {
		return taskDAO.save(task);
	}
	
	@Transactional
	public void deleteTask(int id) {
		taskDAO.deleteTask(id);
	}

}
