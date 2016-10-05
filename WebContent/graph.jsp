<%@page import="java.util.ArrayList"%>
<%@page import="WebApplication.*"%>
<%@page import="WebApplication.graphMySQLConnection"%>
<%@page import="javax.swing.text.html.parser.Entity"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
html *
{
   font-family: Arial !important;
}

.searchField{
text-align: center;
}

input{
   text-align:center;
}
body {
	margin: 0px;

}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #b5b5b5;
    width: 100%;
}

li {
    float: left;
}

li a, .dropbtn {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: #e3e3e3;
}

li.dropdown {
	direction: rtl;
	
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color:#b5b5b5;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #e3e3e3}

.dropdown:hover .dropdown-content {
    display: block;
}

h2 {
    text-align: center;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//d3plus.org/js/d3.js"></script>
<script src="//d3plus.org/js/d3plus.js"></script>
<title>Visuals</title>
</head>
<%
mySQLconnection con = new mySQLconnection();
UserBean user = (UserBean) session.getAttribute("currentUser");
String username = user.getUsername();
%>
<body>
<ul>
  <li><a href="search.jsp">Home</a></li>
  <li><a href="advancedSearch.jsp">Advanced Search</a></li>
  <li><a href="addBook.jsp">Add Publication</a></li>
  <li><a href="shoppingCart.jsp">Shopping Cart</a></li>
  <li><a href="graph.jsp">Visuals</a></li>
  
  
  
  <li style="float:right" class="dropdown">
    <a class="dropbtn"><%=username %></a>
    <div class="dropdown-content">
      <a href="myProfile.jsp">My Profile</a>
      <a href="logoutRedirect.jsp">Log Out</a>

    </div>
  </li>
</ul><br>


<div class = searchField>
<form action = "ControllerServlet" method = "post">
	<input type = "hidden" name = "action" value = "visualSearch" >
 	<input type="text" name="visualSearch" placeholder="Search for a node..">
</form>
</div><br><br>


<div id="viz"></div>


<%graphMySQLConnection connection = new graphMySQLConnection();%>
<%String visualSearchWord = (String)session.getAttribute("visualSearch");
 			System.out.println(visualSearchWord);%>
<script>
 
  var graph = <%=connection.getSearchResults(visualSearchWord)%>

            	
  var visualization = d3plus.viz()
    .container("#viz")
    .type("network")   
    .edges(graph) 
    .id("name")
    .draw()          
</script>

</body>
</html>