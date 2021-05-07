package io.rl.tm_api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rl.tm_api.dao.UserDAO;
import io.rl.tm_api.entity.User;

@Service
public class UserService {
    
    @Autowired
    UserDAO userDAO;

    @Transactional
    public User fetchMyDetails() {
        return userDAO.fetchMyDetails();
    }
    
    @Transactional
    public User fetchUser(int id) {
    	return userDAO.fetchUser(id);
    }

    @Transactional
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Transactional
    public List<User> fetchAllUsers() {
        return userDAO.fetchAllUsers();
    }

    @Transactional
    public User updateUser(User user) {
        return userDAO.save(user);
    }

}
