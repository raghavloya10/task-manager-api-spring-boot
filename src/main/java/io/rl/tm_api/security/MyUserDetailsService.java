package io.rl.tm_api.security;

import java.util.List;
// import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.rl.tm_api.entity.User;
import io.rl.tm_api.jpa.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Optional<User> user = userDAO.getUser(username);
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		return user.map(MyUserDetails::new).get();
    }


    public User fetchUser(int id) {
        return userRepository.findById(id);
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
    
}
