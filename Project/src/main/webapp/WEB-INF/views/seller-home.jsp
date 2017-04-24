<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller Home Page</title>
</head>
<body>
	<h1>Hi, ${seller.firstName}</h1>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a><br/><br/>
	<a href="${contextPath}/seller/add-products.htm">Add Products</a><br/>
	<a href="${contextPath}/seller/view-products.htm?sellerId=${seller.personID}">View Products</a>
</body>
</html>