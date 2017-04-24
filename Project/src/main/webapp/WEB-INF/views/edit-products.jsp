<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Products</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/seller/view-products.htm?sellerId=${sessionScope.seller.personID}">Go back</a>
	
	<h1>Edit Product</h1>
	<form:form action="updateProducts.htm" commandName="product" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.seller.firstName}</td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><form:select disabled="true" path="categories" items="${product.categories}" multiple="true" required="required" /></td>
			</tr>
			<tr>
				<td>
					Product Name:
				</td>
				<td>
					<form:input path="productName" disabled="true" value="${product.productName}" />
				</td>
			</tr>
			<tr>
				<td>
					Product Description:
				</td>
				<td>
					<form:textarea path="productDesc" required="required" value="${product.productDesc}" />
					<font color="red"><form:errors path="productDesc" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Price:
				</td>
				<td>
					<form:input path="productPrice" required="required" value="${product.productPrice}" />
					<font color="red"><form:errors path="productPrice" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Quantity:
				</td>
				<td>
					<form:input path="productQuantity" required="required" value="${product.productQuantity}" />
					<font color="red"><form:errors path="productQuantity" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Product Image:
				</td>
				<td>
					<img src="${product.fileName}" height="150" width="150" style="object-fit: cover;"/><br/>
					<form:input type="file" path="photo" />
				</td>
			</tr>					
			<tr>
				<td colspan="2"><input type="submit" value="Update Product" /></td>
				<td colspan="2"><input type="hidden" name="pid" value="${product.productID}" /></td>
				<td colspan="2"><input type="hidden" name="filename" value="${product.fileName}" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>