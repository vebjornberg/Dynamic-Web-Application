<%@page import="java.util.ArrayList"%>
<%@page import="WebApplication.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- JSP file for adding books for users --%>


<%
UserBean user = (UserBean) session.getAttribute("currentUser");
String username = user.getUsername();
mySQLconnection sql = new mySQLconnection();

//ArrayList<AuthorBean> authors = sql.getAuthors();


		

%>
</head>
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

input[type=text], select {
    width: 50%;
    padding: 12px 20px;
    margin: 8px 0;
    font-size: 14px;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
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

<h2>Add Publication for sale</h2>



<form action = "ControllerServlet" method = "post">
	<input type = "hidden" name = "action" value = addPublication >
	
	<select id="pubType" name="pubType">
		<option value="book">Book</option>
		<option value="article">Article</option>
		<option value="other">Other</option>
	</select>
	
	<select id="authors" name="authors">
		<% 
		for(AuthorBean author: authors){
			String firstName = author.getFirstname();
			String lastName = author.getLastname();
			String fullName = firstName + " " + lastName;
			%>
		<option value=<%=fullName %>><%=fullName %></option>
		<%} %>
	</select>

 	
	<input type="text" name="title" placeholder="Publication Title"><br>
	<input type="text" name="year" placeholder="Release year"><br>
	<input type="text" name="price" placeholder="Price"><br>
	

  
	<input type="submit" value="Add Publication">
</form>




</body>
</html>