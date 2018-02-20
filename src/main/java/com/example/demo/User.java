package com.example.demo;

import javax.persistence.*;

/**
 * Created by pv on 19/2/18.
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String username;

	@Column
	private String password;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
}
