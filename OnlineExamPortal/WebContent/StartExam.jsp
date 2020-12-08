<%@page import="com.vwits.dao.UserLogin"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="com.vwits.dao.QuestionsDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exam-Window</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>
	<%@include file='navbar.html'%>
	<% 
	UserLogin name = new UserLogin();
	String nameOfUser = name.fetchNameOfUser((String)session.getAttribute("username"));
	String msg = (String) session.getAttribute("username");
	String id = (String) request.getAttribute("id");
	int userID = name.fetchIDOfUser((String)(session.getAttribute("username")));
	if(msg != null && id != null){
		out.println("Welcome " + nameOfUser);
		out.println("ID: " + id);
	}else if(msg == null || id == null){
		response.sendRedirect("studenthome.jsp");
	}
	
	QuestionsDAO quest = new QuestionsDAO();
	ResultSet rs = quest.displayQuestions();
	ResultSetMetaData metaData = rs.getMetaData();
	
	
	%>
	<%!String quesID = null; %>
	<h2>Exam on Programing Language</h2>
	<div class="container">
		<table class="table table-borderless"  width="50%">
		<thead>
		<tr>
			<th>Question No</th>
			<th>Question</th>
			<th>Option A</th>
			<th>Option B</th>
			<th>Option C</th>
			<th>Option D</th>
		</tr>
		</thead>
		<tbody>
		<%
			while(rs.next()){
		%>
				<tr>
				<td>
						<%= quesID = (String)  rs.getString(6) %>
				</td>
				<td>
						<%= rs.getString(1) %>
				</td>
				<form method="post" action="ProcessExamServlet">
				<input type="hidden" name="userSession" value="<%=nameOfUser%>">
				<input type="hidden" name="ID" value="<%=userID%>">
				<%
					for(int i = 2;i<= metaData.getColumnCount() -1 ;i++){
						%>
						
						
						<td>
						<div class="form-group">
						<label class="radio-inline"><input class="radio" type="radio" name="ans<%=quesID %>" value="<%= rs.getString(i) %>"><%= rs.getString(i) %></label>
						
						</div>
						</td>
					<%			
						}
					%>
				
				</tr>
				<% 
			
			}
			%>
		
		</tbody>
	</table>
	<input type="submit" class="btn btn-success btn-block" value="Submit Exam">
	</form>
	
	</div>
	
	

</body>
</html>