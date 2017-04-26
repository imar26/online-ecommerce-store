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
	
	<h1>Add new category</h1>
	<form:form action="${contextPath}/admin/addCategory.htm" method="post" commandName="category">

		<table>
			<tr>
				<td>Category Name:</td>
				<td>
					<form:input path="categoryName" required="required" />
					<font color="red"><form:errors path="categoryName" /></font>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Add Category" /></td>
			</tr>
		</table>

	</form:form>
	<%
            }
	%>
</body>
</html>