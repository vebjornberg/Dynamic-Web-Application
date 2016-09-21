<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="WebApplication.*" import="java.sql.DriverManager" import="java.sql.Connection" import= "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
table {
    font-family: arial, sans-serif;
    font-size: 14px;
    border-collapse: collapse;
    width: 40%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:first-child {
    background-color: #dddddd;
}
</style>
<body>

<%
ArrayList<PublicationBean> results = (ArrayList<PublicationBean>) request.getAttribute("AdvancedResults");
%>

<table>
<tr>
	<th>click on each entry to get more info</th>
</tr>

<%for(PublicationBean pubBean : results){
	String title = pubBean.getTitle();%>
		<tr>
			<td><%=title %></td>
		</tr>
		<%}%>
</table>



</body>
</html>