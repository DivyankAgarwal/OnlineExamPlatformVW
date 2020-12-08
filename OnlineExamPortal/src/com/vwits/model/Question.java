package com.vwits.model;

import java.util.Arrays;
import java.util.List;

public class Question {
	int questionNo;
	String question;
	List<String> options;
	String correctAns;
	public Question(int questionNo, String question, List<String> options, String correctAns) {
		super();
		this.questionNo = questionNo;
		this.question = question;
		this.options = options;
		this.correctAns = correctAns;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<String> getOptions() {
		return options;
	}
	public String getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}
	@Override
	public String toString() {
		return "Question [questionNo=" + questionNo + ", question=" + question + ", options=" + options
				+ ", correctAns=" + correctAns + "]";
	}
	
	

}
