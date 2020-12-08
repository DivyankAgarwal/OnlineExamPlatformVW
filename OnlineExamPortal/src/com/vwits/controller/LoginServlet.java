package com.vwits.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vwits.dao.UserLogin;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("logintype");
		UserLogin ul = new UserLogin();
		if(userType.equalsIgnoreCase("student")) {
			String msg = ul.userLogin(username, password, false);
			System.out.println(msg);
			if(msg.equals("NS")) {
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				
			}else if(msg.equals("INVALID")) {
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				
			}else if(msg.equals("S")) {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				response.sendRedirect("studenthome.jsp");
				
			}
		}else if(userType.equalsIgnoreCase("teacher")) {
			String msg = ul.userLogin(username, password, true);
			if(msg.equals("NT")) {
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}else if(msg.equals("INVALID")) {
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			} else if(msg.equals("T")) {
				HttpSession teacherSession = request.getSession(true);
				teacherSession.setAttribute("username", username);
				response.sendRedirect("teacherhome.jsp");
				
				
			}
		}
		
	}

}
