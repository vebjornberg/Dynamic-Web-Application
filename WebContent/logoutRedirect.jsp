<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="WebApplication.*" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Signing out</title>
</head>
<body>
<%
UserBean prevUser = (UserBean)session.getAttribute("currentUser");
ArrayList<PublicationBean> cartBean = (ArrayList<PublicationBean>)session.getAttribute("cart");
System.out.println(prevUser.getUsername() + " has been signed out");

mySQLconnection connection = new mySQLconnection();
connection.addCart(prevUser.getUsername(), cartBean);

session.invalidate();
request.getSession();
//session.setAttribute("cart", cartBean);
// if ( (UserBean)session.getAttribute("currentUser") == null){
// 	System.out.println("Ingen current user");
// }else{
// 	UserBean newUser = (UserBean)session.getAttribute("currentUser");
// 	System.out.println(newUser.getUsername() + " is new??");
// }

%>
<jsp:forward page = "signIn.jsp" />
</body>
</html>