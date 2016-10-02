<%@page import="WebApplication.*" import= "java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<%
UserBean user = (UserBean) session.getAttribute("currentUser");
String username =  (user.getUsername());
mySQLconnection sql = new mySQLconnection();
int totalPrice=0;

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
<center>
<h1>My Cart</h1>
<form action="removefromcart" method="post">
<table>
			
 <tr>
    <td width="200"></td>
    <td width="50"></td>
    <td width="25"></td>
  </tr>
   				<tr height = "20px">
   			
   				<%
       			ArrayList<PublicationBean> cartList =  (ArrayList<PublicationBean>)session.getAttribute("cart");
   				
   				int i = 0;
   				if (!cartList.isEmpty()){
					for (PublicationBean publication : cartList){
						%>
					<% totalPrice += Integer.parseInt(publication.getPrice());
					%>
	   				
	  				<td><a href="<%out.println(publication.getTitle());%>"><%out.println(publication.getTitle()); %></a></td>
	  				<td><a ><%out.println("$" + publication.getPrice()); %></a></td>
					<td><input type="checkbox" name ="cartcheckbox" value ="<%=i%>" ></td>
	   				
	   				
					</tr>
					<%
	   				i++;
					
					}
				}
   				%>
   				<% if(!cartList.isEmpty()){ %>
   					<tr height = "30px">
   					<td>Total Price: </td>
	  				<td><a ><%out.println("$" +totalPrice); %></a></td>
					<td></td>
					</tr>
					<%} %>
   				</table>
   				<% 
   				if(cartList.isEmpty()){ 
   					
   					
   					
   				%>
   				<h2>Your cart has no items</h2>
   				<input type="submit"  name ="action" value="Remove from Cart" disabled="disabled">
				</form>
   				
   				<%} 
   				else{%>
   				
				
  			
	
				
				<input type="submit"  name ="action" value="Remove from Cart">
		</form>
		<% }%>
		<form action="newsearch" method="post">
			<input name = "action" type = "submit" value="Buy items" >
		</form>
		<p style="color:grey"><%=cartList.size()%> item(s) in cart.</p>
	</center>
</body>
</html>