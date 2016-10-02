<%@page import="WebApplication.*" import="java.sql.DriverManager" import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- The website HOME PAGE for searching for books --%>

</head>




<% 
	mySQLconnection sqlConnection = new mySQLconnection();
	String password = sqlConnection.getPassword("vebjorbe");
	UserBean user = (UserBean) session.getAttribute("currentUser");
	String username =  user.getUsername();

%>
<style>
html *
{
   font-family: Arial !important;
}
body {
	margin: 0px;

}

.searchField{
text-align: center;
}

input{
   text-align:center;
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
.styled-select{
text-align: center;
	width: 100%;
    margin: 0 auto;
     height: 34px;

}

.styled-select select {
	text-align: center;
  text-align-last: center;
   border: 1px solid #ccc;
   font-size: 16px;
   height: 34px;
   width: 50%;
}

option {
  text-align: center;

}

input[type=text], select {
	width:50%;
    padding: 12px 20px;
    font-size: 14px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 20%;
    background-color: #0036ff;
    color: white;
    font-size: 14px;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color:#0026b2;
}
</style>
</head>
<body>

<ul>
  <li><a href="search.jsp">Home</a></li>
  <li><a href="advancedSearch.jsp">Advanced Search</a></li>
  <li><a href="addBook.jsp">Add Publication</a></li>
  <li><a href="shoppingCart.jsp">Shopping Cart</a></li>
  
  
  
  <li style="float:right" class="dropdown">
    <a class="dropbtn"><%=username %></a>
    <div class="dropdown-content">
      <a href="myProfile.jsp">My Profile</a>
      <a href="logoutRedirect.jsp">Log Out</a>

    </div>
  </li>
</ul><br>

<h2>Advanced search</h2>


<div class = searchField>
<form action = "ControllerServlet" method = "post">
	<input type = "hidden" name = "action" value = "advancedSearch" >
	

 	<input type="text" name="authorFirstName" placeholder="First name author"><br><br>
	<input type="text" name="authorLastName" placeholder="Last name author"><br><br>
	<input type="text" name="title" placeholder="Publication Title"><br><br>
	<input type="text" name="year" placeholder="Release year"><br><br>
	
	<div class="styled-select">
	<select id="pubType" name="pubType">
		<option value="book">Book</option>
		<option value="article">Article</option>
		<option value="phdthesis">Phdthesis</option>
		<option value="inproceedings">Inproceedings</option>
		<option value="journal">Journal</option>
		<option value="conference">Conference</option>
		<option value="other">Other</option>
	</select>
	</div><br>
  
	<input type="submit" value="Submit">
</form>
</div>




</body>
</html>