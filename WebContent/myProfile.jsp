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
    

    </style>


<%
String username = (String) session.getAttribute("currentUser");
//ArrayString<String> infoFromUsername = infoFromUsername(username;)
//String firstName, lastname, dob, address, email, password, creditCard = infoFromUsername[0], infoFromUsername[1], infoFromUsername[2], osv..   
%>

<h1>Profile - <%=username %></h1><br>

Her you can update user info. NB: username cannot be changed.<br><br> 
<form action='Edit Account' method='post'>
		<div class="container">
		
		<label>First name</label><br>
		<input type="text" name="fname" value= "1"><br><br>
		<label>Last name</label><br>
		<input type="text" name="lname" value= "2"><br><br>
		<label>Date of Birth</label><br>
		<input type=text name="bDate" placeholder="ddmmyyyy" value= "3"><br><br>
		<label>Address</label><br>
		<input type="text" name="address" value= "4"><br><br>	
		<label>Email</label><br>
		<input type="text" name="email"><br><br>
		<label>Password</label><br>
		<input type="password" name="pass"><br><br>
		<label>Credit card number</label><br>
		<input type="text" name="creditCardNr"><br><br>
		</div>
	<input type = 'submit' name='action' value='Confirm Changes'>
</form>

</head>
<body>

</body>
</html>