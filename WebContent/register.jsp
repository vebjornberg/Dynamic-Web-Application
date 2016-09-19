<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DBL - New User</title>

<%-- Her you can register a new user --%>



    <style type="text/css">
    .container label {
	  float:left;
	  width:25%;
	  text-align:right;
	  margin-right:5px;
	}
	.container input {
	  float:left;
	  width:50%;
	}
    

    </style>

</head>
<body>
<center>
	<h1>Register new user</h1><br>
	<form action="RegisterUser" method="post"><br>
		<div class="container">
		<label>User name:</label><input type="text" name="username"><br><br>
		<label>First name:</label><input type="text" name="fname"><br>
		<label>Last name:</label><input type="text" name="lname"><br>
		<label>Date of Birth:</label> <input type=text name="bDate" placeholder="ddmmyyyy"><br><br>
		<label>Address:</label><input type="text" name="address"><br><br>
		
		<label>Email</label><input type="text" name="email"><br>
		<label>Repeat email:</label> <input type="text" name="emailRep"><br><br>
		
		<label>Password:</label><input type="password" name="pass"><br>
		<label>Repeat password:</label><input type="password" name="passRep"><br><br>
		
		<label>Credit card#:</label><input type="text" name="creditCardNr"><br><br>
		
		</div>
		<input type="submit" name = action value="Register user"><br>
	</form>
</center>
</body>
</html>