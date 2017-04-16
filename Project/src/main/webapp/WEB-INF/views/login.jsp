<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/index.htm">Go Back</a><br/>
<h1>Login page</h1>
<form method="post" action="redirectlogin.htm">
  <input type="radio" name="login" value="admin" checked> Login as Admin<br>
  <input type="radio" name="login" value="buyer"> Login as Buyer<br>
  <input type="radio" name="login" value="seller"> Login as Seller<br>
  <input type="text" name="username" placeholder="Username" required />
  <input type="password" name="password" placeholder="Password" required />
  <input type="submit" value="Submit" />
</form>
</body>
</html>