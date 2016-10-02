<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="WebApplication.*" import="java.sql.DriverManager" import="java.sql.Connection" import= "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DLD - My Profile</title>

<%-- JSP for viewing and editing your profile --%>




<%
mySQLconnection con = new mySQLconnection();
UserBean user = (UserBean) session.getAttribute("currentUser");
String username = user.getUsername();

//String firstName, lastname, dob, address, email, password, creditCard = infoFromUsername[0], infoFromUsername[1], infoFromUsername[2], osv..   
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
    color: #252525;
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
    color: #252525;
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

input[type=text], select {
	width:50%;
    padding: 12px 20px;
    font-size: 14px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=password], select {
	width:50%;
    padding: 12px 20px;
    font-size: 14px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input.button1{
    width: 20%;
    background-color: #0036ff;
    color: white;
    font-size: 14px;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
}

input.button1:hover {
    background-color:#0026b2;
    
}

input.button2 {
    display: inline;
 	width: 25%;
    background-color: #b5b5b5;
    color: black;
    font-size: 16px;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
}

input.button2:hover {
     background-color: #cdcdcd;
     box-shadow: 1px 1px 1.5px 2px rgba(0, 0, 0, 0.4), -1px -1px 1.5px 2px rgba(0, 0, 0, 0.4); 
}

.profileButtons{
    text-align: center;

}
.profileButtons form,
.profileButtons form div {
	display:inline;
}

table#table1 {
    width:50%; 
    margin-left:25%; 
    margin-right:25%;
  }
  
table a:link {
	color: #666;
	font-weight: bold;
	text-decoration:none;
}
table a:visited {
	color: #999999;
	font-weight:bold;
	text-decoration:none;
}
table a:active,
table a:hover {
	color: #bd5a35;
	text-decoration:underline;
}
table {
	font-family:Arial;
	color:#666;
	font-size:14px;
	text-shadow: 1px 1px 0px #fff;
	background:#eaebec;
	margin:20px;
	border:#ccc 1px solid;

	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;

	-moz-box-shadow: 0 1px 2px #d1d1d1;
	-webkit-box-shadow: 0 1px 2px #d1d1d1;
	box-shadow: 0 1px 2px #d1d1d1;
}
table th {
	padding:21px 25px 22px 25px;
	border-top:1px solid #fafafa;
	border-bottom:1px solid #e0e0e0;
	font-size:16px;

	background: black;
	background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
	background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
}
table th:first-child {

}
table tr:first-child th:first-child {
	-moz-border-radius-topleft:3px;
	-webkit-border-top-left-radius:3px;
	border-top-left-radius:3px;
}
table tr:first-child th:last-child {
	-moz-border-radius-topright:3px;
	-webkit-border-top-right-radius:3px;
	border-top-right-radius:3px;
}
table tr {
	text-align: center;
	padding-left:20px;
}
table td:first-child {
	text-align: left;
	padding-left:20px;
	border-left: 0;
}
table td {
	padding:18px;
	border-top: 1px solid #ffffff;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;

	background: #fafafa;
	background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
	background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
}
table tr.even td {
	background: #f6f6f6;
	background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
	background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
}
table tr:last-child td {
	border-bottom:0;
}
table tr:last-child td:first-child {
	-moz-border-radius-bottomleft:3px;
	-webkit-border-bottom-left-radius:3px;
	border-bottom-left-radius:3px;
}
table tr:last-child td:last-child {
	-moz-border-radius-bottomright:3px;
	-webkit-border-bottom-right-radius:3px;
	border-bottom-right-radius:3px;
}
table tr:hover td {
	background: #f2f2f2;
	background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
	background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);	
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
      <a href="logoutRedirect.jsp">My Profile</a>
      <a href="">Log Out</a>

    </div>
  </li>
</ul><br>


<h2> Profile Overview - <%=user.getFirstname()%> <%=user.getLastname() %> </h2>


<div class="profileButtons">
    <form action="myProfile.jsp">
		<input type = 'submit' name='action' value='Personal Info' class = "button button2">
	</form>

    <form action="myProfilePublications.jsp">
		<input type = 'submit' name='action' value='Publications added by me' class = "button button2">
	</form>
</div><br>



<div class="profileButtons">
    <form action="ControllerServlet" method = "post">

<table cellspacing='0' id="table1">
	<thead>
		<tr>
			<th>Publication Title</th>
			<th>Author</th>
			<th>Price</th>
			<th>For Sale</th>
		    <th>Sale Count</th>
		    <th>Checkbox</th>
		</tr>
	</thead>
	<tbody>

<%
int i = 0;
ArrayList<PublicationBean> publications = con.getPublicationsAddedByUser(username);
for(PublicationBean publication : publications){
	%>
		<tr>
			<td><%=publication.getTitle() %></td>
			<td><%=publication.getFirstname()%> <%=publication.getLastname() %></td>
			<td><%=publication.getPrice() %></td>
			<td><%=publication.getSale() %></td>
			<td><%=publication.getNumsold() %></td>
			<td><input type="checkbox" name="myPubCheckbox" value="<%=i %>" > </td>
		</tr>
		<%i++;}
		%>
	</tbody>
</table>


    	<input type="submit" name = "action" value="Pause publication" class = "button button1">
    	<input type="submit" name ="action" value="Remove publication from database" class = "button button1">
   	</form>

     
</div><br>



</head>
<body>

</body>
</html>