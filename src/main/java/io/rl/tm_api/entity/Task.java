package io.rl.tm_api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String title;
	
	@ManyToOne(cascade = {
			CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST, CascadeType.REFRESH
	})
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column
	private Boolean completed=false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Task(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Task(int id, Boolean completed) {
		super();
		this.id = id;
		this.completed = completed;
	}
	
	public Task() {
		
	}
	
	public Task(String title) {
		this.title = title;
	}
	
	public int getUserId() {
		if(user == null) {
			return 0;
		}
		return user.getId();
	}

	@Override
	public String toString() {
		return "Task [title=" + title + ", completed=" + completed + "]";
	}
	
}

