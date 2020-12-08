<%@page import="com.vwits.dao.UserLogin"%>
<%@page import="javax.websocket.SendResult"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Home Page</title>
<link rel="stylesheet" href="./custom-css/studenthome.css">
</head>
<style>
</style>
<body>
	<%@include file='navbar.html'%>
	<%!HttpSession session;%>


	<%
		session = request.getSession(false); //Do not create new session
		UserLogin name = new UserLogin();
		String nameOfUser = name.fetchNameOfUser((String) session.getAttribute("username"));
		String msg = (String) session.getAttribute("username");
		if (msg != null) {
	%>

	<div class="container">
		<div class="row">
			<div class="col-md-4">
				Welcome
				<%
				out.println(nameOfUser);
			%>
			</div>
			<div class="col-md-8">
				<a href="LogoutServlet" class="btn btn-danger" id="logoutbtn"> <span
					class="glyphicon glyphicon-log-out"></span> Logout
				</a>
			</div>

		</div>

	</div>
	<%
		} else if (msg == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3 class="panel-title">Student Panel</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form action="studenthome.jsp" method="post">
									<input type="submit" id="vsbtn" value="Update Profile"
										name="submit" class="btn btn-primary disabled"> 
										<input type="submit" value="Change Password" name="submit"
										class="btn btn-success"> 
										<input type="submit" value="Report Issue" name="submit" class="btn btn-warning disabled">
								</form>

							</div>

						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-8 ">
				<form method="post">

					<button type="submit" id="startexambtn"
						class="btn btn-block btn-success" value="Start Exam"
						name="startexam">
						<span class="glyphicon glyphicon-send"></span> Start Exam
					</button>
				</form>
			</div>

		</div>
	</div>
<hr></hr>

	<%
		String buttonClicked = request.getParameter("startexam");
		if (buttonClicked != null) {
			request.setAttribute("user", session.getAttribute("username"));
			request.setAttribute("id", session.getId());
			RequestDispatcher rd = request.getRequestDispatcher("StartExamServlet");
			rd.forward(request, response);

		}
		String panelButton = request.getParameter("submit");
		if(panelButton != null && panelButton.equals("Change Password")){
	%>
	<div class="container">
		<form action="StudentProfileUpdateServlet" method="post">
			<div class="form-group row question-form">
				<label for="oldpwd" class="col-sm-3 col-form-label">Old Password</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" name="oldpwd" required >
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="newpwd" class="col-sm-3 col-form-label">New Password</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" id="newpwd" name="newpwd" onkeyup="check();" required >
				</div>
			</div>
			<div class="form-group row question-form">
				<label for="confnewpwd" class="col-sm-3 col-form-label">Confirm New Password</label>
				<div class="col-sm-5">
					<input type="password" class="form-control" id="confnewpwd" name="confnewpwd" onkeyup="check();" required >
				</div>
			</div>
			<div class="form-group row question-form">
				<div class="col-lg-4">
					<input type="submit" id="updatepwdbutton" class="form-control btn btn-danger btn-sm"  value="Update Password"  >
				</div>
			</div>
		
		</form>
	</div>
	<h5 id="opmsg">
		
	</h5>
	
	<% 
		}
	%>
	
	<script type="text/javascript">
		var check = function(){
			if(document.getElementById('newpwd').value == document.getElementById('confnewpwd').value){
				document.getElementById('opmsg').innerHTML = "New and Confirm matched"
			} else{
				document.getElementById('opmsg').innerHTML = "New and Confirm not matched"
			}
		}
	
	</script>


</body>
</html>
