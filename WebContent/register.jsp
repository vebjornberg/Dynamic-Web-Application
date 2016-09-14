<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- Her you can register a new user --%>

</head>
<body>

<form action="RegisterUser" method="post">
User name: <input type="text" name="username"><br>
First Name:<input type="text" name="fname"><br>
Last Name:<input type="text" name="lname"><br>
Date of Birth: <input type=text name="bDate" placeholder="ddmmyyyy">
Password : <input type="password" name="pass"><br>
<input type="submit"><br>
</form>

</body>
</html>