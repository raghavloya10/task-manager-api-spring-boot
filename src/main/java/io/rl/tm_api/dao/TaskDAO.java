package io.rl.tm_api.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import io.rl.tm_api.entity.Task;
import io.rl.tm_api.entity.User;

@Repository
public class TaskDAO {
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private UserDAO userDAO;
    
    private Session currentSession;
    
    public List<Task> fetchAllTasks() {
        currentSession = entityManager.unwrap(Session.class);
        User user = userDAO.getCurrentlyAuthenticatedUser();
        Query<Task> theQuery = 
        		currentSession.createQuery("from Task t where t.user=user", Task.class);
        List<Task> tasks = theQuery.getResultList();
        System.out.println(">>>>"+user);
        return tasks;
    }
    
    public Task fetchTask(int id) {
        currentSession = entityManager.unwrap(Session.class);
        User user = userDAO.getCurrentlyAuthenticatedUser();
        Task task = currentSession.get(Task.class, id);
        if(task.getUser().getId()!=user.getId()) {
        	return null;
        }
        return task;        
    }  
    
    public Task save(Task task) {
        currentSession = entityManager.unwrap(Session.class);        
        User user = userDAO.getCurrentlyAuthenticatedUser();
        if(task.getUser()==null) {
           task.setUser(user);
           System.out.println("Hello!");
        }
        currentSession.saveOrUpdate(task);
        return task;
    }


    public void deleteTask(int id) {
        currentSession = entityManager.unwrap(Session.class);
        User user = userDAO.getCurrentlyAuthenticatedUser();
        Task task = this.fetchTask(id);
        if(task.getUser().getId()!=user.getId()) {
        	return;
        }
        var theQuery = currentSession.createQuery(
            "delete from Task t where t.id=:taskId");
        theQuery.setParameter("taskId", id);
        theQuery.executeUpdate();
        
    }

}
