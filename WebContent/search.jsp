<%@page import="WebApplication.mySQLconnection" import="java.sql.DriverManager" import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- The website HOME PAGE for searching for books --%>

</head>
<body>

<p>Search for title or whatevah<p/>
<form ></form>

<% 
	mySQLconnection sqlConnection = new mySQLconnection();
	String password = sqlConnection.getPassword("vebjorbe");
	sqlConnection.setUser("torjus.saethre@gmail.com", "torjussa", "Torjus", "Saethre", "50 Coogee street", "123456789", "26041993", "torjussapass");
	
	String username = (String) session.getAttribute("currentUser");
	
	
	
%>

welcome <%=username %>!

</body>
</html>