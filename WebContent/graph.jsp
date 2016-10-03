<%@page import="java.util.ArrayList"%>
<%@page import="WebApplication.graphMySQLConnection"%>
<%@page import="javax.swing.text.html.parser.Entity"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//d3plus.org/js/d3.js"></script>
<script src="//d3plus.org/js/d3plus.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="viz"></div>


<%graphMySQLConnection connection = new graphMySQLConnection();%>

<script>

  var graph = <%=connection.graphStore()%>

            	
  var visualization = d3plus.viz()
    .container("#viz")
    .type("network")   
    .edges(graph) 
    .id("name")
    .draw()          
</script>

</body>
</html>