<%@page import="WebApplication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DLD - My Profile</title>

<%-- JSP for viewing and editing your profile --%>

 <style type="text/css">
    .container label {
	  float:left;
	  width:25%;
	  margin-right:5px;
	}
	.container input {
	  float:left;
	  width:50%;
	}
	
	input[type=submit] {
    width: 50%;
    background-color: #5252f4;
    color: white;
    font-size: 14px;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 12px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #434395;
}
    

    </style>


<%
mySQLconnection con = new mySQLconnection();
UserBean user = (UserBean) session.getAttribute("currentUser");
String username = user.getUsername();

//String firstName, lastname, dob, address, email, password, creditCard = infoFromUsername[0], infoFromUsername[1], infoFromUsername[2], osv..   
%>

<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
    width: 50%;

}

li {
    float: left;
}

li a, .dropbtn {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: #555;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #333;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
    color: white;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #555}

.dropdown:hover .dropdown-content {
    display: block;
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
      <a href="">Log Out</a>

    </div>
  </li>
</ul><br>


<h2> Profile Overview - <%=user.getFirstname()%> <%=user.getLastname() %> </h2>
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
		<input type="text" name="email" value = "<%=user.getAddress() %>"><br><br>
		<label>Password</label><br>
		<input type="password" name="pass" value= "<%=user.getPassword() %>"><br><br>
		<label>Credit card number</label><br>
		<input type="text" name="creditCardNr" value = "<%=user.getCreditCard() %>"><br><br>
		</div>
	<input type = 'submit' name='action' value='Confirm Changes'>
</form>

</head>
<body>

</body>
</html>