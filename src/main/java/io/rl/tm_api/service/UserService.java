package io.rl.tm_api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.rl.tm_api.entity.User;
import io.rl.tm_api.jpa.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User fetchMyDetails() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).get();
    }
    
    @Transactional
    public User fetchUser(int id) {
    	return userRepository.findById(id);
    }

    @Transactional
    public User addUser(User user) {
        user.setId(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // System.out.println(">>>"+user.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    public User deleteUser(int id) {
        return userRepository.deleteById(id);
    }

    @Transactional
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
