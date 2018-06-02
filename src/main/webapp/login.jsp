<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ include file = "header.jsp"%>
	<div class="card border mb-3 mx-auto" style="max-width: 30rem;">
  	<div class="card-header bg-transparent border display-4">Login</div>
  	<form method="POST" action="userlogin">
  	<div class="card-body">
  	<div class="form-group">
    	<label for="inputName" class= "font-weight-bold">Username</label>
    	<input type="username" name="user" class="form-control" id="inputName" aria-describedby="nameHelp" required placeholder="Enter Username">
    	<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
  	</div>
  	<div class="form-group">
    	<label for="inputPassword1" class="font-weight-bold">Password</label>
    	<input type="password" name="password" class="form-control" id="inputPassword1" required placeholder="Password">
  	</div>
  	<button type="submit" class="btn btn-secondary">Login</button>
  	<input type="hidden" name="uri" value=<%= request.getRequestURI() %> />
  	</div>
  	</form>
  	
</div>
    <script src="js/jquery-3.3.1.slim.min.js"></script>
   	<script src="js/popper.min.js"></script>
   	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>