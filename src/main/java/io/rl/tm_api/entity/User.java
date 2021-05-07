package io.rl.tm_api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "roles")
	private String roles;
	
	@OneToMany(mappedBy = "user", cascade = {
					CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH
				})
	private List<Task> tasks;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public void addTask(Task task) {
		if(this.tasks == null) {
			this.tasks = new ArrayList<>();
		}		
		this.tasks.add(task);
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
		
	}	

	public User(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.password = u.getPassword();
		this.active = u.getActive();
		this.roles = u.getRoles();
	}
	
	@Override
	public String toString() {
		return username+" - "+firstName+lastName;
	}
}
