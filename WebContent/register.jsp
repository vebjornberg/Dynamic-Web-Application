<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LDB - New User</title>

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
		<label>Address:</label><input type="text" name="adress"><br><br>
		
		<label>Email</label><input type="text" name="email" placeholder="Must contain @ to be valid"><br>
		<label>Repeat email:</label> <input type="text" name="emailRep" placeholder="Emails must be equal"><br><br>
		
		<label>Password:</label><input type="password" name="pass" placeholder="Must be at least 6 characters"><br>
		<label>Repeat password:</label><input type="password" name="passRep" placeholder = "Passwords must be equal"><br><br>
		
		<label>Credit card#:</label><input type="text" name="creditCardNr" placeholder="Must be 8 digits"><br><br>
		
		</div>
		<input type="submit" name = action value="Register user"><br>
	</form>
</center>
</body>
</html>