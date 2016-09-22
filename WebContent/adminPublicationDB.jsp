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
<title>Publication DB</title>
</head>
<body><center>
<h1>All publications</h1>
<%
ArrayList<PublicationBean> allPublications = (ArrayList<PublicationBean>)session.getAttribute("allPublications");
%>
<form action="bannuser" method="post">
<table>
<tr height="30px">
	<td style="font-style: italic;" width="150px">Title</td>
	<td style="font-style: italic;" width="50px">For Sale</td>
	<td style="font-style: italic;" width="50px">#Sold</td>
	<td style="font-style: italic;" width="50px">#RemovedFromCart</td>
	<td width="20px"></td>
</tr>

<% int i = 0;
for (PublicationBean pub: allPublications){
	%>

<tr>
	<td><%out.println(pub.getTitle()); %></td>
	 <td><%=pub.getNumsold() %></td>
	 <td><%=pub.getNumremoved()%></td>
	 <td><%=pub.getSale()%></td>
	 <td><input type="checkbox" name ="publCheckbox" value ="<%=i%>" ></td>


</tr>
<%i++;} %>
</table>
 <input type="submit" name="action" value="Toggle banned">
</form>

</center></body>
</html>