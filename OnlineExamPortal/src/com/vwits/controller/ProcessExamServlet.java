package com.vwits.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vwits.dao.ExamResultDAO;
import com.vwits.dao.QuestionsDAO;
import com.vwits.model.Exam;

/**
 * Servlet implementation class ProcessExamServlet
 */
@WebServlet("/ProcessExamServlet")
public class ProcessExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProcessExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs = new QuestionsDAO().displayQuestions();
		List<String> questionIds = new ArrayList<String>();
		List<String> correctAns = new QuestionsDAO().getCorrectAnswers(); 
		String userSession = request.getParameter("userSession");
		int id = Integer.parseInt(request.getParameter("ID"));
		int totalScore = 0;
		try {
			while(rs.next()) {
				questionIds.add(rs.getString(6));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String key : questionIds) {
			
			String ques1 = request.getParameter("ans"+key); 
			if(correctAns.contains(ques1)) {
				totalScore++;
			}
		}
		Exam exam = new Exam(id, Integer.toString(totalScore));
		ExamResultDAO examResultDAO = new ExamResultDAO();
		int row = examResultDAO.storeResult(exam);
		if(row == 1) {
			System.out.println("Record inserted Successfully");
		} else if(row == 99) {
			System.out.println("Score inserted Successfully");
			
		}else if(row == 999) {
			System.out.println("Score Updated Successfully");
		}
		response.sendRedirect("login.jsp");
		
		
		
	}

}
