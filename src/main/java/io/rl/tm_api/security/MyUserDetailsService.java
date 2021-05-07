package io.rl.tm_api.security;

// import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.rl.tm_api.dao.UserDAO;
import io.rl.tm_api.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> user = userDAO.getUser(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
		return user.map(MyUserDetails::new).get();
    }
    
}
