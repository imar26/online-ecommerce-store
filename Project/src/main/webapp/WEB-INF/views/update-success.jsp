<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Updation Successfull</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	Congratulations! Your product is successfully updated.
	<a href="${contextPath}/seller/view-products.htm?sellerId=${sessionScope.seller.personID}">Click here</a> to view products.
</body>
</html>