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


<%ArrayList<JSONObject> graph2 = new ArrayList<JSONObject>(); 

graphMySQLConnection connection = new graphMySQLConnection();
//out.println(connection);
graph2 = connection.graphStore();
out.println(graph2);%>

<script>

  var graph = [ {"source": "p1", "target": "p2"},
                {"source": "p1", "target": "pb4"},
                {"source": "p1", "target": "a2"},
                {"source": "p1", "target": "e2"},
                {"source": "a2", "target": "p5"},
                {"source": "p5", "target": "e2"},
	            {"source": "p5", "target": "e3"},
                {"source": "e3", "target": "p3"},
                {"source": "p3", "target": "pb4"},
                {"source": "pb4", "target": "p6"},
                {"source": "p6", "target": "p2"},
                {"source": "p6", "target": "a4"},
                {"source": "a4", "target": "p2"},
                {"source": "p9", "target": "e2"},
                {"source": "p9", "target": "a4"}]

            	
  var visualization = d3plus.viz()
    .container("#viz")
    .type("network")   
    .edges(graph) 
    .id("name")
    .draw()          
</script>

</body>
</html>