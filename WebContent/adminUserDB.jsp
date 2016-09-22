<%@page import="WebApplication.*" import= "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%if((Boolean)session.getAttribute("adm")==null){ %>
<jsp:forward page = "signIn.jsp" />
<%} %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User DB</title>
</head>
<body><center>
<%
ArrayList<UserBean> allUsers = (ArrayList<UserBean>)session.getAttribute("allUsers");
%>
<table>
<tr>
	<td width="200px"></td>
	<td width="100px"></td>
	<td width="20px"></td>
</tr>
<% for (UserBean user: allUsers){
	%>

<tr>
	<td><%out.println(user.getUsername()); %></td>
	 <td><%out.println(user.getUsername()); %></td>
	 <td><input type="checkbox" name ="userCheckbox" value ="box" ></td>


</tr>
<%} %>
</table>

</center></body>
</html>