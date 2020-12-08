package com.vwits.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vwits.dao.StudentPanelDAO;

/**
 * Servlet implementation class StudentProfileUpdateServlet
 */
@WebServlet("/StudentProfileUpdateServlet")
public class StudentProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StudentProfileUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		StudentPanelDAO studentPanelDAO = new StudentPanelDAO();
		int row = studentPanelDAO.changePassword(username, oldpwd,newpwd);
		if(row != 0 && row != -1) {
			request.setAttribute("msg", "Password Changed Successfully");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "Error");
			request.getRequestDispatcher("studenthome.jsp").forward(request, response);
			
		}
	}

}
