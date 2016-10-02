<%@page import="WebApplication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LDB - My Profile</title>

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



<div class = searchField>
<form action='Edit Account' method='post'>
		<div class="container">
		
		<label>First name</label><br>
		<input type="text" name="fname" value= "<%=user.getFirstname() %>"><br><br>
		<label>Last name</label><br>
		<input type="text" name="lname" value= "<%=user.getLastname() %>"><br><br>
		<label>Date of Birth</label><br>
		<input type=text name="bDate" placeholder="ddmmyyyy" value= "<%=user.getDateOfBirth() %>"><br><br>
		<label>Address</label><br>
		<input type="text" name="address" value= "<%=user.getAddress()%>"><br><br>	
		<label>Email</label><br>
		<input type="text" name="email" value = "<%=user.getEmail() %>"><br><br>
		<label>Password</label><br>
		<input type="password" name="pass" value= "<%=user.getPassword() %>"><br><br>
		<label>Credit card number</label><br>
		<input type="text" name="creditCardNr" value = "<%=user.getCreditCard() %>"><br><br>
		</div>
	<input type = 'submit' name='action' value='Confirm Changes' class = "button button1">
</form>
</div>

</head>
<body>

</body>
</html>