<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="WebApplication.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signing out</title>
</head>
<body>
<%
UserBean prevUser = (UserBean)session.getAttribute("currentUser");
System.out.println(prevUser.getUsername() + " has been signed out");

session.invalidate();
request.getSession();
// if ( (UserBean)session.getAttribute("currentUser") == null){
// 	System.out.println("Ingen current user");
// }else{
// 	UserBean newUser = (UserBean)session.getAttribute("currentUser");
// 	System.out.println(newUser.getUsername() + " is new??");
// }

%>
<jsp:forward page = "signIn.jsp" />
</body>
</html>