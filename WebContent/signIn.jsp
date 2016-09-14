<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Digital Library Database</title>

<%-- 
	The first page you get sent to when opening the website.
	Here you have can either sign in, or register a new user. 
--%>


</head>
	<body>
		<center>
		
		<t1>Digital Library Database</t1>
		<br><br>
		<form action="login" method="post">
			Username : <input type="text" name="username"><br>
			Password : <input type="password" name="pass"><br>
			<input type="submit" value = "Sign in"><br>
		</form>
		
		<br><br>
		<form action="newUser" method="post" >
			<input type="submit" value = "Create new user">
		</form>
		
		</center>
	</body>
</html>