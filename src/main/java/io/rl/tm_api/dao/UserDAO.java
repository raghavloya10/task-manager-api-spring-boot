package io.rl.tm_api.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import io.rl.tm_api.entity.User;

@Repository
public class UserDAO {
    
    @Autowired
    private EntityManager entityManager;

    private Session currentSession;
        
    @Transactional
    public User getCurrentlyAuthenticatedUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   	 	String username = ((UserDetails) auth.getPrincipal()).getUsername();
   	 	User currentUser = getUser(username).get();
    	return currentUser;
    }
    
    @Transactional
    public Optional<User> getUser(String username) {
        currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery =
				currentSession.createQuery("from User u where u.username=:name", User.class);
        theQuery.setParameter("name", username);
		
		// execute query and get result list
		List<User> users = theQuery.getResultList();
        if(users.isEmpty()) {
            return null;
        }
		Optional<User> user = Optional.of(users.get(0));
        return user;
    }

    public User fetchMyDetails() {
        currentSession = entityManager.unwrap(Session.class);
        return getCurrentlyAuthenticatedUser();
    }

    public List<User> fetchAllUsers() {
        currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }
    
    public User fetchUser(int id) {
        currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class, id);
        return user;
    }
    
    public User save(User user) {
        currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
        return user;
    }

    public void deleteUser(int id) {
        currentSession = entityManager.unwrap(Session.class);
        var theQuery = currentSession.createQuery(
            "delete from User where id=:userId");
        theQuery.setParameter("userId", id);
        theQuery.executeUpdate();
        
    }

}
