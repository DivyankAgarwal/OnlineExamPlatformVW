package com.vwits.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vwits.dao.NewQuestionDAO;
import com.vwits.model.Question;

/**
 * Servlet implementation class NewQuestionServlet
 */
@WebServlet("/NewQuestionServlet")
public class NewQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public NewQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String question = request.getParameter("question");
		String opta = request.getParameter("opta");
		String optb = request.getParameter("optb");
		String optc = request.getParameter("optc");
		String optd = request.getParameter("optd");
		String correctAns = request.getParameter("correctans");
		int qid = Integer.parseInt(request.getParameter("qid"));
		List<String> options = new ArrayList<String>();
		options.add(opta);
		options.add(optb);
		options.add(optc);
		options.add(optd);
		Question newQuestion = new Question(qid, question, options, correctAns);
		NewQuestionDAO newQuestionDAO = new NewQuestionDAO();
		newQuestionDAO.insertNewQuestion(newQuestion);
		out.println("Question Inserted Successfully");
	}

}
