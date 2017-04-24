<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Single Product</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script>
	$(document).ready(function() {
		$("#cart").on('click', function(e) {
			e.preventDefault();
			var productID = $(this).data('product');
			var qty = $("#qty").val();
			var qty_cart = $("#qty_cart").val();

			if(qty_cart == "" ) {
				alert("Quantity cannot be empty");
			} else if (qty_cart < 1) {
				alert("Quantity cannot be less than 1");	
			} else if (Math.abs(qty) < Math.abs(qty_cart)) {
				alert("Quantity cannot be more than stock value");
			} else {
				$.ajax({
			        url: 'addToCart.htm',		        
			        contentType: "application/json; charset=utf-8",
			        data: { 'qty_cart': qty_cart, 'productID' : productID },
			        type: 'GET',
			        cache: false,
			        success: function (response) {
				        qty = Math.abs(qty) - Math.abs(qty_cart);
				        $("#qty").val(qty);			        				        
			        }
			    });
			}
		});
	});
</script>
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
	<a href="${contextPath}/buyer/view-all-products.htm">Go back</a>
	<a href="${contextPath}/buyer/cart.htm?uid=${user.personID}">Cart</a>

	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<img src="${product.fileName}" width="100%" />
			</div>
			<div class="col-md-6">
				<h1>${product.productName }</h1>
				<p>${product.productDesc }</p>
				<p>$${product.productPrice }</p>
				<p>In Stock: <input id="qty" type="text" value="${product.productQuantity }" readonly="true" /></p>
				<p>Sold By: ${product.seller.companyName }</p>
				<p>Quantity: <input id="qty_cart" type="number" min="1" /></p>
				<p><a href="#" id="cart" data-product="${product.productID}">Add to Cart</a>
			</div>
		</div>
	</div>
</body>
</html>