package io.rl.tm_api.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.rl.tm_api.entity.Task;
import io.rl.tm_api.entity.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{
    
    List<Task> findAllByUser(User user);


    
}
