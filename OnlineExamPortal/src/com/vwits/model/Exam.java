package com.vwits.model;

public class Exam {
	int id;
	String score;
	
	public Exam(int id, String score) {
		super();
		this.id = id;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	

}
