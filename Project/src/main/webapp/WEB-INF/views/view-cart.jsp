<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$(".delete")
								.on(
										'click',
										function(e) {
											e.preventDefault();
											var productID = $(this).parent()
													.parent().data('id');
											var prd_qty = $(this).parent()
													.parent().find(
															'.prd_quantity')
													.text();
											var qty = $(this).parent().parent()
													.find('.quantity').text();
											var totalPrice = $("#total").text();

											$
													.ajax({
														url : 'updateValueInCart.htm',
														contentType : "application/json; charset=utf-8",
														data : {
															'productID' : productID,
															'prd_qty' : prd_qty
														},
														type : 'GET',
														cache : false,
														success : function(
																response) {
															$(
																	"#row-"
																			+ response)
																	.fadeOut();
															totalPrice = (Math
																	.abs(totalPrice)
																	.toFixed(2))
																	- (Math
																			.abs(qty)
																			.toFixed(2));
															if (totalPrice == 0) {
																$("#message")
																		.text(
																				"No Products in Cart");
																$("#buy")
																		.fadeOut();

																$("#table")
																.fadeOut();															
															}
															$("#total").text(
																	totalPrice);
														}
													});
										});
					});
</script>
</head>
<body>
	<%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String role = (String) session.getAttribute("role");
            if (role == "buyer") {
    %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/buyer/view-all-products.htm">View All
		Products</a>
	<a href="${contextPath}/buyer/cart.htm?uid=${user.personID}">Cart</a>
	<a href="${contextPath}/buyer/order.htm?uid=${user.personID}">Orders</a>

	<h1>Your Cart</h1>
	<c:choose>
		<c:when test="${!empty products}">
			<c:set var="total" value="${0}" />
			<table id="table" border="1" cellpadding="5">
				<tr>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Product Quantity</th>
					<th>Total Price</th>
					<th>Action</th>
				</tr>
				<c:forEach var="product" items="${products}">
					<tr id="row-${product.product.productID}"
						data-id="${product.product.productID}">
						<td>${product.product.productName}</td>
						<td>${product.product.productPrice}</td>
						<td class="prd_quantity">${product.quantity}</td>
						<td class="quantity"><c:set var="totalPrice"
								value="${product.product.productPrice * product.quantity}" />${product.product.productPrice * product.quantity}</td>
						<td><a href="#" class="delete">Remove</a></td>
						<c:set var="total" value="${total+totalPrice}" />
					</tr>
				</c:forEach>
			</table>
			<h3>
				Total Calculated Price: $<span id="total"><c:out
						value="${total}" /></span>
			</h3>
			<h3 id="message"></h3>
			<br />
			<a id="buy" href="${contextPath}/buyer/buy-now.htm">Buy Now!</a>
		</c:when>
		<c:otherwise>
			<c:out value="No Products in Cart" />
		</c:otherwise>
	</c:choose>
	<%
            }
	%>
</body>
</html>