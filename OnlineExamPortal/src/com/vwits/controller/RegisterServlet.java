	package com.vwits.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vwits.dao.UserRegisterDAO;
import com.vwits.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("logintype");
		System.out.println(userType);
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		if(userType.equalsIgnoreCase("student")) {
			user.setIsTeacher('N');
			UserRegisterDAO userRegister = new UserRegisterDAO();
			System.out.println(user);
			String msg = userRegister.registerUser(user);
			if(msg.equals("S")) {
				out.println("Registered Successfully");
				response.sendRedirect("login.jsp");
			}else if(msg.equals("F")) {
				out.println("Error in Registering");
			}else {
				out.println("Server encountered problem. Try again later.");
			}
		}else {
			user.setIsTeacher('Y');
			UserRegisterDAO userRegister = new UserRegisterDAO();
			String msg = userRegister.registerUser(user);
			if(msg.equals("S")) {
				out.println("Registered Successfully");
			}else if(msg.equals("F")) {
				out.println("Error in Registering");
			}else {
				out.println("Server encountered problem. Try again later.");
			}
			
		}
		
	}

}
