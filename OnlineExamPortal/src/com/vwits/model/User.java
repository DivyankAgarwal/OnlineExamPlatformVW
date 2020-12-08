package com.vwits.model;

public class User {
	int id;
	String name;
	String username;
	String password;
	char isTeacher;
	public User() {
		
	}
	public User(int id, String name,String username, String password, char isTeacher) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.isTeacher = isTeacher;
	}
	public char getIsTeacherOption() {
		return isTeacher;
	}
	public void setIsTeacher(char option) {
		this.isTeacher = option;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", isTeacher=" + isTeacher + "]";
	}
	

}
