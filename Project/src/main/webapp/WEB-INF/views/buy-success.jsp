<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Success</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
	
	Congratulations! Your Order has been placed successfully. <br/>
	<a href="${contextPath}/buyer/view-all-products.htm">Click here</a> to shop more. <br/>
	<a href="${contextPath}/buyer/order.htm">Click here</a> to view orders. <br/>
</body>
</html>