<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Examify - Login</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./custom-css/login.css">
</head>
<body>
<%@include file='navbar.html'%>
	<div class="container">
	<div class="row">
			<div class="col-lg-8">
				<div class="card" id="card">
					<div class="card-body cb-color">
						<h2 class="card-title" id="titlefont" align="center">Examify Registration Portal</h2>
						<hr class="titlebelowline">
							<form action="RegisterServlet" method="post">
								<div class="form-group ">
									<input type="text" class="form-control" name="id" placeholder="ID" required>
								</div>
								<div class="form-group ">
									<input type="text" class="form-control" name="name" placeholder="Enter Full Name" required>
								</div>
								<div class="form-group inner-addon left-addon">
									<input type="text" class="form-control" name="username" placeholder="Username" required>
									<span class="glyphicon glyphicon-user "></span>
								</div>
								<div class="form-group inner-addon left-addon">
									<input type="password" class="form-control" name="password" placeholder="Password" required>
									<span class="glyphicon glyphicon-lock "></span>
								</div>
								<div class="form-group">
									<div class="radio" id="radio">
										<label class="radio-inline"><input class="radio" type="radio" name="logintype" value="student" required>Student</label>
										<label class="radio-inline"><input class="radio" type="radio" name="logintype" value="teacher" required >Teacher</label>
									</div>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-success btn-block" value="Register">
								</div>
							</form>
						</div>
					<div class="card-footer text-muted text-center">
						<span id="footertxt">Already Registered?</span>
						<br>
						<a href="login.jsp"><span id="footertxt1">Login</span></a>
					</div>
					
				</div>
			
			</div>
		</div>
	
	</div>

</body>
</html>