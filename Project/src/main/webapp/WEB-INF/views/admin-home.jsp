<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body>
	<a href="logout.htm">Logout</a>
	<h1>Hi <span style="text-transform:capitalize;">${admin.username},</span></h1>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/view-sellers.htm">View Sellers</a> 	
</body>
</html>