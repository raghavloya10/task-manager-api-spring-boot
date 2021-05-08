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

import io.rl.tm_api.entity.User;
import io.rl.tm_api.exception.EntityNotFoundException;
import io.rl.tm_api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/me")
    public User fetchMyDetails() {
        return userService.fetchMyDetails();
    }
    
    @GetMapping("/all")
    public List<User> fetchAllUsers() {
    	return userService.fetchAllUsers();
    }
    
    @GetMapping("/{id}")
    public User fetchUser(@PathVariable int id) throws EntityNotFoundException {
    	return userService.fetchUser(id);
    }
    
    @PostMapping("")
    public User addUser(@RequestBody User user) {
    	return userService.addUser(user);
    }
    
    @PutMapping("")
    public User updateUser(@RequestBody User user) throws EntityNotFoundException {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) throws EntityNotFoundException {
        return userService.deleteUser(id);
    }
    
}
