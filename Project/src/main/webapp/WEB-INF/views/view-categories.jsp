<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Categories</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$(document).on('click', '.delete', function(e) {
			e.preventDefault();
			var cid = $(this).parent().parent().data('id');
			//alert(aid);
			$.ajax({
		        url: 'deleteCategory.htm',		        
		        contentType: "application/json; charset=utf-8",
		        data: { 'cid': cid },
		        type: 'GET',
		        cache: false,
		        success: function (response) {
		        	$("#row-"+response).fadeOut();
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
            if (role == "admin") {
    %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/admin/manage-categories.htm">Go Back</a>
	
	<h1>Categories List</h1>
	<table id="table" border="1" cellpadding="5">
		<tr>
			<th>Category Name</th>
			<th>Action</th>
		</tr>
		<c:forEach var="category" items="${categories}">
			<tr id="row-${category.categoryID}" data-id="${category.categoryID}">
				<td>${category.categoryName}</td>
				<td>
					<a href="#" class="delete">Delete Category</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%
            }
	%>
</body>
</html>