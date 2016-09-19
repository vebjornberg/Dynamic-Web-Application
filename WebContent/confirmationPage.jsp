<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<%-- JSP file to send users to when they press their confirmation link in the email --%>

<body><center>
<% //if (Profile has already been confirmed){%>

<%-- <h1>Your profile has already been confirmed</h1>   --%>
<% 
//}	else{
%>
<h1>Your profile is now confirmed and ready to use</h1>
<%//} %>


<form action="goToSignin" method="post">
<button value="Sign In" ></button></form>
</center></body>
</html>