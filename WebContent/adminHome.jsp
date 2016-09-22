<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%--Checks if admin, if nay, redirects to normal sign in --%>

<%if((Boolean)session.getAttribute("adm")==null){ %>
<jsp:forward page = "signIn.jsp" />
<%} %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Dashboard</title>
</head>
<body><center>
<h1>Admin Dashboard</h1>

<form action="newUser" method="post" >
			<input size="20px" type="submit" name ="action" value = "User DB">
			<input size="20px" type="submit" name = "action" value = "Publication DB">
		</form>

</center></body>
</html>