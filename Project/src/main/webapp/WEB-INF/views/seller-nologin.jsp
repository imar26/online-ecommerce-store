<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller cannot login</title>
</head>
<body>
	Hey! You cannot login as the admin has not yet approved you. Please try again later.
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/login.htm">Click here</a> to go back.
</body>
</html>