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
<title>Adm - User DB</title>
</head>
<body><center>
<h1>All users</h1>
<%
ArrayList<UserBean> allUsers = (ArrayList<UserBean>)session.getAttribute("allUsers");
%>
<form action="bannuser" method="post">
<table border="1">
<tr height="30px">
	<td style="font-style: italic;" width="150px">Username</td>
	<td style="font-style: italic;" width="100px">Banned</td>
	<td width="20px"></td>
</tr>

<% int i = 0;
for (UserBean user: allUsers){
	%>

<tr>
	<td><%out.println(user.getUsername()); %></td>
	 <td><%=(user.getBanned()==0) ? ("-"):("banned")%></td>
	 <td><input type="checkbox" name ="userCheckbox" value ="<%=i%>" ></td>


</tr>
<%i++;} %>
</table>
 <input type="submit" name="action" value="Toggle banned">
</form>

</center></body>
</html>