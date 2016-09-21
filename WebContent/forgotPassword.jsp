<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Enter email to get password</h1> <%-- Klartekst..? --%>
<form action="forgotPassword" method="post">
<input type="text" placeholder="example@email.com" name="forgottenPwMail">
<input type="submit" name="action" value="Get password">
<%if ((Boolean)session.getAttribute("getPasswordPressed")){ %>
<p>If the email exists in the database an email has been sent.</p>
<%} %>
</form>
<br>
<form action="goToSignin" method="post">
<input type="submit" name="action" value ="Sign in here">
</form>
</body>
</html>