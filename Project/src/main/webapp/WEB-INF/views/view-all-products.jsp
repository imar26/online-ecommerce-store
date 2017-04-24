<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller View Products</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
	body {
		margin: 10px;
	}
	.container {
		width: 90%;
		margin: 0 auto;
		padding-left: 15px;
		padding-right: 15px;
	}
	.row {
		margin-left: -15px;
		margin-right: -15px;
	}
	.col-md-6 {
	    width: 50%;
	    float: left;
	    padding-right: 15px;
    	padding-left: 15px;
    	position: relative;    	
	}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/buyer/buyer-home.htm">Go back</a>
	<a href="${contextPath}/buyer/cart.htm?uid=${user.personID}">Cart</a>
	<a href="${contextPath}/buyer/order.htm">Orders</a>	
	
	<h1>List of Products</h1>
	
	<div class="container">
		<div class="row">
			<c:forEach var="product" items="${products}">
				<c:if test="${!empty product.categories}">
				<div class="col-md-6">
					<div class="w3-container">	
						<div class="w3-panel w3-card-4">
							<div>
								<img src="${product.fileName}" width="100%" height="150" style="padding: 10px 0 5px 0; object-fit: cover;" /> <br/>
								<h1><a href="single-product.htm?id=${product.productID }">${product.productName }</a></h1>
								<p>${product.productDesc }</p>
								<p>Category: ${product.categories}</p>
								<p><i>$${product.productPrice }</i>
								<p><b>Sold By: </b><i>${product.seller.companyName}</i></p>
							</div>
						</div>
					</div>
				</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>