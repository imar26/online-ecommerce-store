<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/buyer/view-all-products.htm">View All Products</a>
	<a href="${contextPath}/buyer/cart.htm?uid=${user.personID}">Cart</a>
	<a href="${contextPath}/buyer/order.htm">Orders</a>
	
	<h1>Order History</h1>
	<c:choose>
		<c:when test="${!empty hashmap}">
			<c:forEach items="${hashmap}" var="entry">
			<c:set var="total" value="${0}" />
			<c:out value="Order ID: ${entry.key}" />
			<br/>
			<table id="table" border="1" cellpadding="5">
				<tr>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Total Price</th>
					<th>Date</th>
					<th>Seller Name</th>
					<th>Action</th>
				</tr>
				<c:set var="view_pdf" value="true" />
				<c:forEach var="order" items="${entry.value}">			
					<tr id="row-${order.orderID}" data-id="${order.orderID}">
						<td>${order.product.productName}</td>
						<td>${order.quantity}</td>
						<td><c:set var="totalPrice" value="${order.quantity * order.product.productPrice}" />${order.quantity * order.product.productPrice}</td>
						<td><fmt:formatDate type="date" value="${order.date}" /></td>
						<td>${order.product.seller.companyName}</td>
						<td class="action_${order.orderID}">
							<c:if test="${view_pdf == 'true'}">
								<a href="/buyer/pdf.htm?id=${order.orderID}" class="view_pdf">View PDF</a>
							</c:if>
						</td>
						<c:set var="total" value="${total+totalPrice}" />
						<c:set var="view_pdf" value="false" />
					</tr>
				</c:forEach>
			</table>
			<h3>Total Price: $<c:out value="${total}" /></h3>
			<br/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<c:out value="No Orders" />
		</c:otherwise>
	</c:choose>
</body>
</html>