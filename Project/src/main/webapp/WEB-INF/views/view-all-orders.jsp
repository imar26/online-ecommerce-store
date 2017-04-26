<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Orders</title>
</head>
<body>
	<%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String role = (String) session.getAttribute("role");
            if (role == "seller") {
    %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a><br/><br/>
	<a href="${contextPath}/seller/seller-home.htm">Go back</a>
	
	<h1>View Orders</h1>
	<table id="table" border="1" cellpadding="5">
		<tr>
			<th>Order ID</th>
			<th>Product Name</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Total Price</th>
			<th>Date</th>
			<th>Order Placed By</th>
		</tr>
		<c:forEach var="order" items="${orders}">
			<c:if test="${order.product.seller.personID == sellerID}" >
			<tr>
				<td>${order.orderID }</td>
				<td>${order.product.productName }</td>
				<td>${order.quantity }</td>
				<td>${order.product.productPrice }</td>
				<td>${order.product.productPrice * order.quantity }</td>
				<td><fmt:formatDate type="date" value="${order.date}" /></td>
				<td>${order.user.firstName}&nbsp;${order.user.lastName}</td>
			</tr>
			</c:if>
		</c:forEach>
	</table>
	<%
            }
	%>
</body>
</html>