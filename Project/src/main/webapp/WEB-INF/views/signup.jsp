<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup Page</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/index.htm">Go Back</a><br/>
<h1>SignUp page</h1>
<form method="post" action="redirectsignup.htm">
  <input type="radio" name="signup" value="buyer"> SignUp as Buyer<br>
  <input type="radio" name="signup" value="seller"> SignUp as Seller<br>
  <input type="submit" value="Submit" />
</form>
</body>
</html>