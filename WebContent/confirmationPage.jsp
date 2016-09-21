<%@ page import ="WebApplication.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Confirm your profile</title>
</head>

<%ControllerServlet servlet;
mySQLconnection sql =new mySQLconnection();


String username = request.getParameter("user");
String hash = request.getParameter("hash");
System.out.println("Parameters from url: " +username + " , " + hash);

UserBean currentUserBean = sql.getUserInfo(username);



 %>



<%-- JSP file to send users to when they press their confirmation link in the email --%>

<body><center>
<%
if (currentUserBean.getActivated()==0){
	System.out.println("Hash stored in bean; "+ currentUserBean.getConfirmationHash());
	
	if (currentUserBean.getConfirmationHash().equals(hash)){
		currentUserBean.setActivated(1);
		System.out.println("UserBean has been activated");
%>

<h1>Your profile is now confirmed and ready to use</h1>

<%
	}
	else{
		%><h1>Not valid confirmation link</h1><% 
	}
}
else{
%>
<h1>Your profile has already been activated</h1>



<%System.out.println("Already activated");
} %>

<form action="goToSignin" method="post">
<input type="submit" name="action" value ="Sign in here">
</form>
</center></body>
</html>