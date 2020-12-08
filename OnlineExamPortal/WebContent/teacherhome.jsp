<%@page import="com.vwits.dao.UserLogin"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="com.vwits.dao.AdminPanelDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teacher - Admin Panel</title>
<link rel="stylesheet" href="./custom-css/teacherhome.css">
</head>
<body>

	<%@include file='navbar.html'%>
	<%!HttpSession teacherSession;%>
	<%
		teacherSession = request.getSession(false);
		UserLogin name = new UserLogin();
		String nameOfUser = name.fetchNameOfUser((String) teacherSession.getAttribute("username"));
		if(nameOfUser !=  null){
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-4" >
				Welcome <% out.println(nameOfUser);%>
			</div>
			<div class="col-md-8" >
			<a href="LogoutServlet" class="btn btn-danger" id="logoutbtn">
			<span class="glyphicon glyphicon-log-out"></span>
			Logout
			</a>
			</div>
			
			
		</div>
	
	</div>
	<%}else if(nameOfUser == null){
			response.sendRedirect("login.jsp");
		
	}
	%>
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-3">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">Admin Panel</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-10">
								<form action="teacherhome.jsp" method="post">
									<input type="submit" id="vsbtn" value="View Students"
										name="submit" class="btn btn-primary"> <input
										type="submit" value="View Result" name="submit"
										class="btn btn-success">
									<input type="submit" value="Add Exam" name="submit"
										class="btn btn-warning">
								</form>

							</div>

						</div>
					</div>
				</div>

			</div>

		</div>

	</div>
		<%
		String buttonClicked = request.getParameter("submit");
		if (buttonClicked != null && buttonClicked.equals("View Students")) {
	%>
	<div class="container">
		<table class="table table-striped table-borderless" width="50%">
			<thead>
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
				</tr>
			</thead>
			<tbody>
				<%
					AdminPanelDAO admin = new AdminPanelDAO();
						Map<Integer, String> students = admin.getAllStudents();

						for (Map.Entry<Integer, String> entry : students.entrySet()) {
				%>
				<tr>
					<td><%=entry.getKey()%></td>
					<td><%=entry.getValue()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<%
		} else if (buttonClicked != null && buttonClicked.equals("View Result")) {
	%>
	<div class="container">
		<table class="table table-striped table-borderless" width="50%">
			<thead>
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<%
					AdminPanelDAO admin = new AdminPanelDAO();
						ResultSet rs = admin.getAllStudentMarks();
						while (rs.next()) {
				%>
				<tr>
					<td><%=rs.getInt(2)%></td>
					<td><%=rs.getString(1)%></td>
					<td><%=rs.getString(3)%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<%
		} else if(buttonClicked != null && buttonClicked.equals("Add Exam")){
			
	%>
	<hr></hr>
	<div class="container">
		<form action="NewQuestionServlet" method="post">
		<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Q ID</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="qid" required >
				</div>
		</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Question</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="question" required>
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Option A</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="opta" required>
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Option B</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="optb" required>
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Option C</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="optc" required>
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Option D</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="optd" required>
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="inputQuestion" class="col-sm-3 col-form-label">Correct Ans</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="correctans" required>
				</div>
			</div>
			<div class="form-group row align-items-center ">
				<div class="submitbutton">
					<input type="submit" class="form-control btn btn-success" value="Submit Question" >
				</div>
			</div>
			
		
		</form>
	</div>
	<%} %>
</body>
</html>