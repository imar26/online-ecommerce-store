<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller View Products</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function() {
	$(document).on('click','.delete',function(e) {
		e.preventDefault();
		var pid = $(this).parent().parent().data('id');
		$.ajax({
			url : 'deleteProduct.htm',
			contentType : "application/json; charset=utf-8",
			data : {
				'pid' : pid
			},
			type : 'GET',
			cache : false,
			success : function(
					response) {
					$("#row-"+response).fadeOut();														
			}
		});
	});
});
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/seller/seller-home.htm">Go back</a>

	<h1>List of Products</h1>
	<c:choose>
		<c:when test="${!empty products}">
			<table id="table" border="1" cellpadding="5">
				<tr>
					<th>Product Name</th>
					<th>Product Description</th>
					<th colspan="2">Action</th>
				</tr>
				
				<c:forEach var="product" items="${products}">
					<c:if test="${!empty product.categories}">
					<tr id="row-${product.productID}" data-id="${product.productID}">
						<td>${product.productName}</td>
						<td>${product.productDesc}</td>
						<td class="edit"><a
							href="edit-products.htm?id=${product.productID}">Edit Product</a>
						</td>
						<td><a class="delete" href="#">Delete Product</a></td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<c:out value="No Products Found." />
		</c:otherwise>
	</c:choose>
</body>
</html>