<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/seller/seller-home.htm">Go Back</a><br/><br/>
	
	<h1>Add a new product</h1>
	<form:form action="addProducts.htm" commandName="product" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Posted By</td>
				<td>${sessionScope.seller.firstName}</td>
			</tr>
			<tr>
				<td>Category:</td>
				<td><form:select path="categories" items="${categories}" multiple="true" required="required" /></td>
			</tr>
			<tr>
				<td>
					Product Name:
				</td>
				<td>
					<form:input path="productName" required="required" />
					<font color="red"><form:errors path="productName" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Product Description:
				</td>
				<td>
					<form:textarea path="productDesc" required="required" />
					<font color="red"><form:errors path="productDesc" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Price:
				</td>
				<td>
					<form:input path="productPrice" required="required" />
					<font color="red"><form:errors path="productPrice" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Quantity:
				</td>
				<td>
					<form:input path="productQuantity" required="required" />
					<font color="red"><form:errors path="productQuantity" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Product Image:
				</td>
				<td>
					<form:input type="file" path="photo" required="required" />
					<font color="red"><form:errors path="photo" /></font>
				</td>
			</tr>					
			<tr>
				<td colspan="2"><input type="submit" value="Add Product" /></td>
				<td colspan="2"><input type="hidden" name="seller-name" value="${sessionScope.seller.personID}" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>